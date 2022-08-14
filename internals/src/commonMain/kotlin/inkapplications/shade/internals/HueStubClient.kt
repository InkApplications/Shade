package inkapplications.shade.internals

import inkapplications.shade.serialization.HueResponse
import inkapplications.shade.serialization.V1HueResponse
import kotlinx.serialization.KSerializer

/**
 * A stubbed out client used for testing delegation
 */
object HueStubClient: HueHttpClient {
    override suspend fun <REQUEST, RESPONSE> sendRequest(
        method: String,
        pathSegments: Array<out String>,
        responseSerializer: KSerializer<HueResponse<RESPONSE>>,
        body: REQUEST?,
        requestSerializer: KSerializer<REQUEST>?
    ): RESPONSE = TODO("Not yet implemented")

    override suspend fun <REQUEST, RESPONSE> sendV1Request(
        method: String,
        pathSegments: Array<out String>,
        responseSerializer: KSerializer<List<V1HueResponse<RESPONSE>>>,
        body: REQUEST?,
        requestSerializer: KSerializer<REQUEST>?
    ): RESPONSE = TODO("Not yet implemented")
}
