package inkapplications.shade.entertainment.parameters

import inkapplications.shade.entertainment.structures.StreamProxyMode
import inkapplications.shade.structures.ResourceReference
import kotlinx.serialization.Serializable

/**
 * Stream proxy configuration for creating an entertainment configuration.
 */
@Serializable
data class StreamProxyCreateParameters(
    /**
     * Proxy mode used for this group.
     */
    val mode: StreamProxyMode,

    /**
     * Reference to the device acting as proxy.
     *
     * The proxy node relays entertainment traffic and should be located in or
     * close to all entertainment lamps in this group.
     * Writing this property sets the proxy mode to "manual".
     * Can be a BridgeDevice or ZigbeeDevice type.
     * Not allowed to be combined with mode "auto".
     */
    val node: ResourceReference? = null,
)
