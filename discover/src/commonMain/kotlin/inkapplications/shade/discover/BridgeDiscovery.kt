package inkapplications.shade.discover

import inkapplications.shade.discover.structures.Bridge

/**
 * Hue bridge discovery functions
 */
interface BridgeDiscovery {
    /**
     * Get a list of Hue Bridges available on the network.
     */
    suspend fun getDevices(): List<Bridge>
}
