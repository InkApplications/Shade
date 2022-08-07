package inkapplications.shade.discover.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A discovered Hue Bridge.
 */
@Serializable
data class Bridge(
    /**
     * The unique ID of this hue bridge.
     */
    val id: BridgeId,

    /**
     * The local network IP address of the bridge.
     */
    @SerialName("internalipaddress")
    val localIp: String,

    /**
     * The port to be used for the Hue API.
     *
     * Note: This field was not always available on older bridges, and will
     * default to an insecure 80 HTTP port if not specified.
     */
    val port: Int = 80,
)
