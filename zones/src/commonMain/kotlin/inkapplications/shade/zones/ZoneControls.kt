package inkapplications.shade.zones

import inkapplications.shade.structures.ResourceId
import inkapplications.shade.zones.structures.Zone

/**
 * Actions to get and configure Zones on the Hue system.
 */
interface  ZoneControls {
    /**
     * Get a list of all zones configured on the Hue Bridge.
     */
    suspend fun listZones(): List<Zone>

    /**
     * Get information for a specific Zone.
     */
    suspend fun getZone(id: ResourceId): Zone
}
