package inkapplications.shade.entertainment.structures

import inkapplications.shade.structures.ResourceReference
import kotlinx.serialization.Serializable

/**
 * Stream proxy configuration for an entertainment configuration.
 *
 * The proxy node relays entertainment traffic and should be located in or
 * close to all entertainment lamps in the group.
 */
@Serializable
data class StreamProxy(
    /**
     * Proxy mode used for this group.
     */
    val mode: StreamProxyMode,

    /**
     * Reference to the device acting as proxy.
     *
     * The proxy node set by the application (manual) or selected by the bridge (auto).
     * Writing this property sets the proxy mode to "manual".
     * Can be a BridgeDevice or ZigbeeDevice type.
     */
    val node: ResourceReference,
)
