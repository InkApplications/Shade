package inkapplications.shade.groupedlights

import inkapplications.shade.groupedlights.structures.GroupedLight
import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData

internal class ShadeGroupedLights(
    private val hueHttpClient: HueHttpClient,
): GroupedLightControls {
    override suspend fun listGroups(): List<GroupedLight> {
        return hueHttpClient.getData("resource", "grouped_light")
    }
}
