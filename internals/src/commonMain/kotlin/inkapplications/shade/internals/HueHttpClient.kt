package inkapplications.shade.internals

import inkapplications.shade.serialization.HueResponse
import inkapplications.shade.structures.HostnameNotSetException
import inkapplications.shade.structures.UnauthorizedException
import inkapplications.shade.structures.ResponseDecodingError
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
     * @throws ResponseDecodingError if the response body is unable to be decoded.
     * @throws HostnameNotSetException if the client has not been configured with a hostname.
     * @throws UnauthorizedException if the client's authentication is invalid.
     * @throws NetworkException if an error occurs while communicating with the API.
     */
    suspend fun <T> getDeserializedData(vararg pathSegments: String, serializer: KSerializer<HueResponse<T>>): T
}

/**
 * Make a GET request to the hue bridge.
 *
 * @throws ResponseDecodingError if the response body is unable to be decoded.
 * @throws HostnameNotSetException if the client has not been configured with a hostname.
 * @throws UnauthorizedException if the client's authentication is invalid.
 * @throws NetworkException if an error occurs while communicating with the API.
 */
suspend inline fun <reified T> HueHttpClient.getData(vararg pathSegments: String): T {
    return getDeserializedData<T>(*pathSegments, serializer = serializer())
}
