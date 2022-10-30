package inkapplications.shade.groupedlights

import inkapplications.shade.groupedlights.structures.GroupedLight
import inkapplications.shade.structures.ResourceId

/**
 * Actions to get and control grouped lights.
 */
interface GroupedLightControls {
    /**
     * Get a list of the grouped lights configured on the Hue bridge
     *
     * @return A list of all grouped lights configured on the bridge.
     */
    suspend fun listGroups(): List<GroupedLight>

    /**
     * Get details about a single light group by ID.
     *
     * @param id The v2 unique id of the grouped light to fetch information for.
     * @return State and capability information about the grouped light.
     */
    suspend fun getGroup(id: ResourceId): GroupedLight
}
