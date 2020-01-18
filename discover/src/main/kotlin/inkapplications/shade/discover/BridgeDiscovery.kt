package inkapplications.shade.discover

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.GET

/**
 * Endpoint to discover hue Bridges on the network.
 *
 * This is the N-UPNP discovery strategy, and requires a working internet
 * connection, unlike local UPNP.
 */
interface BridgeDiscovery {
    /**
     * Get Bridges reported on the network.
     */
    @GET("nupnp")
    suspend fun getDevices(): List<Device>
}

/**
 * A Bridge device on the network.
 *
 * @param id A Unique Identifier for the bridge.
 * @param ip The internal IP address of the bridge.
 */
@JsonClass(generateAdapter = true)
data class Device(
    val id: String,
    @Json(name = "internalipaddress") val ip: String
) {
    /**
     * A base-URL that can be used to connect to the hue bridge locally.
     *
     * Note: Hue bridges don't support https :sadface:
     */
    val url: String = "http://$ip/"
}
