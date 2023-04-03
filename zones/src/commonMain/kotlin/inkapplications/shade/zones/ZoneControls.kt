package inkapplications.shade.zones

import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.zones.parameters.ZoneCreateParameters
import inkapplications.shade.zones.parameters.ZoneUpdateParameters
import inkapplications.shade.zones.structures.Zone

/**
 * Actions to get and configure Zones on the Hue system.
 */
interface  ZoneControls {
    /**
     * Get a list of all zones configured on the Hue Bridge.
     *
     * @return a list of all configured zones on the Hue bridge.
     */
    suspend fun listZones(): List<Zone>

    /**
     * Get information for a specific Zone.
     *
     * @param id The v2 resource ID of the Zone to get information for.
     * @return informtation about the zone with ID [id]
     */
    suspend fun getZone(id: ResourceId): Zone

    /**
     * Update a Zone's definition
     *
     * @param id the v2 resource ID of the zone to be updated.
     * @return a reference to the updated zone.
     */
    suspend fun updateZone(id: ResourceId, parameters: ZoneUpdateParameters): ResourceReference

    /**
     * Create a new Zone on the Hue Bridge.
     *
     * @param parameters Configuration to define the new zone.
     * @return A referenceto the newly created zone.
     */
    suspend fun createZone(parameters: ZoneCreateParameters): ResourceReference

    /**
     * Remove a Zone from the Hue Bridge.
     *
     * @param id the v2 resource id of the zone to be removed.
     * @return a reference to the removed zone.
     */
    suspend fun deleteZone(id: ResourceId): ResourceReference
}
