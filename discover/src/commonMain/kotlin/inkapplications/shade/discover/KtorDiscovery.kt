package inkapplications.shade.discover

import inkapplications.shade.discover.structures.Bridge
import inkapplications.shade.structures.ApiStatusError
import inkapplications.shade.structures.NetworkException
import inkapplications.shade.structures.SerializationError
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

/**
 * Implements bridge discovery using a local ktor client
 */
internal class KtorDiscovery(
    private val client: HttpClient,
): BridgeDiscovery {
    override suspend fun getDevices(): List<Bridge> {
        val httpResponse = try {
            client.get {
                url {
                    host = "discovery.meethue.com"
                    protocol = URLProtocol.HTTPS
                }
                accept(ContentType.Application.Json)
            }
        } catch (e: Throwable) {
            throw NetworkException("Unknown Error making API Request for discovery", e)
        }

        if (!httpResponse.status.isSuccess()) throw ApiStatusError(
            code = httpResponse.status.value,
        )

        return try {
            httpResponse.body()
        } catch (e: Throwable) {
            throw SerializationError("Unable to parse discovery response", e)
        }
    }
}
