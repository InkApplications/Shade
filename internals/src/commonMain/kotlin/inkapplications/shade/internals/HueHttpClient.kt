package inkapplications.shade.internals

import inkapplications.shade.serialization.HueResponse
import inkapplications.shade.serialization.V1HueResponse
import inkapplications.shade.structures.HostnameNotSetException
import inkapplications.shade.structures.UnauthorizedException
import inkapplications.shade.structures.SerializationError
import inkapplications.shade.structures.NetworkException
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer

/**
 * Internal client used for making authenticated requests to the Hue bridge.
 */
interface HueHttpClient {
    /**
     * Makes a GET request to the hue bridge.
     *
     * Note: To avoid the serializer, use the [getData] extension.
     *
     * @param pathSegments A list of strings to url encode and use as the path.
     * @param serializer Serializer to use when decoding the data from the http response.
     * @throws SerializationError if the response body is unable to be decoded.
     * @throws HostnameNotSetException if the client has not been configured with a hostname.
     * @throws UnauthorizedException if the client's authentication is invalid.
     * @throws NetworkException if an error occurs while communicating with the API.
     */
    suspend fun <RESPONSE> getDeserializedData(
        vararg pathSegments: String,
        serializer: KSerializer<HueResponse<RESPONSE>>,
    ): RESPONSE

    /**
     * Make a PUT request to the hue bridge
     *
     * Note: To avoid specifying serializers, use the [putData] extension.
     *
     * @param body Request data to be encoded and send to the endpoint
     * @param pathSegments A list of strings to url encode and use as the path.
     * @param requestSerializer Serializer to use when encoding the request body.
     * @param responseSerializer Serializer to use when decoding the data from the http response.
     * @throws SerializationError if the response body is unable to be decoded.
     * @throws HostnameNotSetException if the client has not been configured with a hostname.
     * @throws UnauthorizedException if the client's authentication is invalid.
     * @throws NetworkException if an error occurs while communicating with the API.
     */
    suspend fun <REQUEST, RESPONSE> putDeserializedData(
        body: REQUEST,
        vararg pathSegments: String,
        requestSerializer: KSerializer<REQUEST>,
        responseSerializer: KSerializer<HueResponse<RESPONSE>>,
    ): RESPONSE

    /**
     * Make a POST request to the hue bridge
     *
     * Note: To avoid specifying serializers, use the [postData] extension.
     *
     * @param body Request data to be encoded and send to the endpoint
     * @param pathSegments A list of strings to url encode and use as the path.
     * @param requestSerializer Serializer to use when encoding the request body.
     * @param responseSerializer Serializer to use when decoding the data from the http response.
     * @throws SerializationError if the response body is unable to be decoded.
     * @throws HostnameNotSetException if the client has not been configured with a hostname.
     * @throws UnauthorizedException if the client's authentication is invalid.
     * @throws NetworkException if an error occurs while communicating with the API.
     */
    suspend fun <REQUEST, RESPONSE> postDeserializedData(
        body: REQUEST,
        vararg pathSegments: String,
        requestSerializer: KSerializer<REQUEST>,
        responseSerializer: KSerializer<HueResponse<RESPONSE>>,
    ): RESPONSE

    /**
     * Make a POST request to the hue bridge as a V1 endpoint.
     *
     * @param body Request data to be encoded and send to the endpoint
     * @param pathSegments A list of strings to url encode and use as the path.
     * @param requestSerializer Serializer to use when encoding the request body.
     * @param responseSerializer Serializer to use when decoding the data from the http response.
     * @throws SerializationError if the response body is unable to be decoded.
     * @throws HostnameNotSetException if the client has not been configured with a hostname.
     * @throws UnauthorizedException if the client's authentication is invalid.
     * @throws NetworkException if an error occurs while communicating with the API.
     */
    suspend fun <REQUEST, RESPONSE> postV1DeserializedData(
        body: REQUEST,
        vararg pathSegments: String,
        requestSerializer: KSerializer<REQUEST>,
        responseSerializer: KSerializer<List<V1HueResponse<RESPONSE>>>,
    ): RESPONSE
}

/**
 * Make a GET request to the hue bridge.
 *
 * @throws SerializationError if the response body is unable to be decoded.
 * @throws HostnameNotSetException if the client has not been configured with a hostname.
 * @throws UnauthorizedException if the client's authentication is invalid.
 * @throws NetworkException if an error occurs while communicating with the API.
 */
suspend inline fun <reified T> HueHttpClient.getData(vararg pathSegments: String): T {
    return getDeserializedData(
        pathSegments = pathSegments,
        serializer = serializer()
    )
}

/**
 * Make a PUT request to the hue bridge.
 *
 * @param body Request data to be encoded and send to the endpoint
 * @param pathSegments A list of strings to url encode and use as the path.
 * @throws SerializationError if the response body is unable to be decoded.
 * @throws HostnameNotSetException if the client has not been configured with a hostname.
 * @throws UnauthorizedException if the client's authentication is invalid.
 * @throws NetworkException if an error occurs while communicating with the API.
 */
suspend inline fun <reified REQUEST, reified RESPONSE> HueHttpClient.putData(
    body: REQUEST,
    vararg pathSegments: String
): RESPONSE {
    return putDeserializedData(
        body = body,
        pathSegments = pathSegments,
        requestSerializer = serializer(),
        responseSerializer = serializer(),
    )
}

/**
 * Make a POST request to the hue bridge.
 *
 * @param body Request data to be encoded and send to the endpoint
 * @param pathSegments A list of strings to url encode and use as the path.
 * @throws SerializationError if the response body is unable to be decoded.
 * @throws HostnameNotSetException if the client has not been configured with a hostname.
 * @throws UnauthorizedException if the client's authentication is invalid.
 * @throws NetworkException if an error occurs while communicating with the API.
 */
suspend inline fun <reified REQUEST, reified RESPONSE> HueHttpClient.postData(
    body: REQUEST,
    vararg pathSegments: String
): RESPONSE {
    return postDeserializedData(
        body = body,
        pathSegments = pathSegments,
        requestSerializer = serializer(),
        responseSerializer = serializer(),
    )
}
