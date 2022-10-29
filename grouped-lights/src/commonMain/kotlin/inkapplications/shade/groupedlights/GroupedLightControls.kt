package inkapplications.shade.groupedlights

import inkapplications.shade.groupedlights.structures.GroupedLight

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
}
