package inkapplications.shade.internals

import inkapplications.shade.serialization.HueResponse
import inkapplications.shade.structures.*
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import subatomic.Atomic

/**
 * Implements HTTP functionality to the hue API as a configurable container.
 */
internal class ConfigurableHttpClient(
    hostname: String? = null,
    applicationKey: ApplicationKey? = null,
    securityStrategy: SecurityStrategy = SecurityStrategy.PlatformTrust,
    private val platformModule: PlatformModule,
): HueHttpClient, HueConfigurationContainer {
    private val json = Json {
        ignoreUnknownKeys = true
    }
    private val defaultClient = createHttpClient(securityStrategy)
    private val httpClient = Atomic(defaultClient)
    private val hostName = Atomic(hostname)
    private val applicationKey = Atomic(applicationKey)

    override suspend fun <RESPONSE> getDeserializedData(
        vararg pathSegments: String,
        serializer: KSerializer<HueResponse<RESPONSE>>
    ): RESPONSE {
        val httpResponse = hueRequest(HttpMethod.Get, pathSegments, null)

        return serializer.parseResponse(httpResponse)
    }

    override suspend fun <REQUEST, RESPONSE> putDeserializedData(
        body: REQUEST,
        vararg pathSegments: String,
        requestSerializer: KSerializer<REQUEST>,
        responseSerializer: KSerializer<HueResponse<RESPONSE>>
    ): RESPONSE {
        val requestBody = try {
            json.encodeToString(requestSerializer, body)
        } catch (e: Throwable) {
            throw SerializationError("Error thrown while serializing request body.", e)
        }
        val httpResponse = hueRequest(HttpMethod.Put, pathSegments, requestBody)

        return responseSerializer.parseResponse(httpResponse)
    }

    private suspend fun <T> KSerializer<HueResponse<T>>.parseResponse(httpResponse: HttpResponse): T {
        val bodyText = httpResponse.bodyAsText()
        val response = try {
            json.decodeFromString(this, bodyText)
        } catch (e: Throwable) {
            throw SerializationError("Error thrown while deserializing response body.", e)
        }
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

    private suspend fun hueRequest(
        method: HttpMethod,
        pathSegments: Array<out String>,
        body: String?,
    ): HttpResponse {
        val hostName = hostName.value ?: throw HostnameNotSetException
        val response = try {
            httpClient.value.request {
                this.method = method
                setBody(body)
                url {
                    host = hostName
                    encodedPathSegments = listOf("clip", "v2", *pathSegments)
                    applicationKey.value?.run { headers.append("hue-application-key", key) }
                    protocol = URLProtocol.HTTPS
                }
            }
        } catch (e: Throwable) {
            throw NetworkException("Unknown Error making API Request", e)
        }

        if (response.status in setOf(HttpStatusCode.Forbidden, HttpStatusCode.Unauthorized)) {
            throw UnauthorizedException()
        }

        return response
    }

    override fun setHost(hostname: String, securityStrategy: SecurityStrategy) {
        httpClient.value = createHttpClient(securityStrategy)
        hostName.value = hostname
    }

    override fun setApplicationKey(key: ApplicationKey) {
        applicationKey.value = key
    }

    private fun createHttpClient(securityStrategy: SecurityStrategy) = HttpClient(
        engineFactory = platformModule.createEngine(securityStrategy)
    ) {
        install(ContentNegotiation) {
            json(json)
        }
    }
}
