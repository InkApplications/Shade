package inkapplications.shade.internals

import inkapplications.shade.serialization.HueResponse
import inkapplications.shade.serialization.V1HueResponse
import inkapplications.shade.structures.*
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kimchi.logger.KimchiLogger
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

/**
 * Implements HTTP functionality to the hue API as a configurable container.
 */
internal class ConfigurableHttpClient(
    private val configurationContainer: HueConfigurationContainer,
    private val platformModule: PlatformModule,
    private val json: Json,
    private val logger: KimchiLogger,
): HueHttpClient {
    val httpClient by CachedProperty(configurationContainer.securityStrategy::value) { key ->
        createHttpClient(key)
    }

    override suspend fun <REQUEST, RESPONSE> sendRequest(
        method: String,
        pathSegments: Array<out String>,
        responseSerializer: KSerializer<HueResponse<RESPONSE>>,
        body: REQUEST?,
        requestSerializer: KSerializer<REQUEST>?
    ): RESPONSE {
        val requestBody = if (body != null && requestSerializer != null) {
            createRequestBody(body, requestSerializer)
        } else null

        val httpResponse = hueRequest(HttpMethod(method), BaseUrl.v2(*pathSegments), requestBody)

        return responseSerializer.parseResponse(httpResponse)
    }

    override suspend fun <REQUEST, RESPONSE> sendV1Request(
        method: String,
        pathSegments: Array<out String>,
        responseSerializer: KSerializer<List<V1HueResponse<RESPONSE>>>,
        body: REQUEST?,
        requestSerializer: KSerializer<REQUEST>?
    ): RESPONSE {
        val requestBody = if (body != null && requestSerializer != null) {
            createRequestBody(body, requestSerializer)
        } else null
        val httpResponse = hueRequest(HttpMethod(method), BaseUrl.v1(*pathSegments), requestBody)

        return responseSerializer.parseV1Response(httpResponse)
    }

    private fun <REQUEST> createRequestBody(
        body: REQUEST,
        requestSerializer: KSerializer<REQUEST>,
    ): String {
        return try {
            json.encodeToString(requestSerializer, body).also {
                logger.debug("Request Json encoded as: $it")
            }
        } catch (e: Throwable) {
            throw SerializationError("Error thrown while serializing request body.", e)
        }
    }

    private suspend fun <T> KSerializer<HueResponse<T>>.parseResponse(httpResponse: HttpResponse): T {
        val bodyText = httpResponse.bodyAsText()
        val response = try {
            json.decodeFromString(this, bodyText)
        } catch (e: Throwable) {
            throw SerializationError("Error thrown while deserializing response body.", e)
        }
        logger.debug("Decoded response: $response")
        when {
            response is HueResponse.Error -> throw ApiError(
                code = httpResponse.status.value,
                errors = response.errors.map { it.description }
            )
            !httpResponse.status.isSuccess() -> throw ApiStatusError(
                code = httpResponse.status.value,
            )
            response is HueResponse.Success -> return response.data
            else -> throw UnexpectedStateException("Unhandled response")
        }
    }

    private suspend fun <T> KSerializer<List<V1HueResponse<T>>>.parseV1Response(httpResponse: HttpResponse): T {
        val bodyText = httpResponse.bodyAsText()
        val response = try {
            json.decodeFromString(this, bodyText)
        } catch (e: Throwable) {
            throw SerializationError("Error thrown while deserializing response body.", e)
        }
        logger.debug("Decoded response: $response")

        when {
            response.any { it is V1HueResponse.Error } -> throw ApiError(
                code = httpResponse.status.value,
                errors = response.filterIsInstance<V1HueResponse.Error>()
                    .map { "V1 Error: (${it.error.type}) response.error.description" }
            )
            !httpResponse.status.isSuccess() -> throw ApiStatusError(
                code = httpResponse.status.value,
            )
            response.any { it is V1HueResponse.Success } -> return response
                .first { it is V1HueResponse.Success }
                .let { it as V1HueResponse.Success }
                .success
            else -> throw UnexpectedStateException("Unhandled response")
        }
    }

    private suspend fun hueRequest(
        method: HttpMethod,
        pathSegments: Array<out String>,
        body: String?,
    ): HttpResponse {
        val hostName = configurationContainer.hostname.value ?: throw HostnameNotSetException
        val response = try {
            httpClient.request {
                this.method = method
                body?.run(::setBody)
                url {
                    host = hostName
                    encodedPathSegments = pathSegments.toList()
                    configurationContainer.authToken.value?.run {
                        logger.debug("Attaching Application key to request")
                        headers.append("hue-application-key", applicationKey)
                    }
                    protocol = URLProtocol.HTTPS

                    logger.debug { "--> ${method.value} ${buildString()}" }
                    body?.run(logger::debug)
                }
            }
        } catch (e: Throwable) {
            throw NetworkException("Unknown Error making API Request", e)
        }

        logger.debug("<-- ${response.status.value}")

        if (response.status in setOf(HttpStatusCode.Forbidden, HttpStatusCode.Unauthorized)) {
            throw UnauthorizedException()
        }

        return response
    }

    private fun createHttpClient(securityStrategy: SecurityStrategy) = HttpClient(
        engineFactory = platformModule.createEngine(securityStrategy)
    ) {
        install(ContentNegotiation) {
            json(json)
        }
    }
}
