package inkapplications.shade.groupedlights

import inkapplications.shade.groupedlights.structures.GroupedLight
import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.structures.ResourceId

internal class ShadeGroupedLights(
    private val hueHttpClient: HueHttpClient,
): GroupedLightControls {
    override suspend fun listGroups(): List<GroupedLight> {
        return hueHttpClient.getData("resource", "grouped_light")
    }

    override suspend fun getGroup(id: ResourceId): GroupedLight {
        return hueHttpClient.getData<List<GroupedLight>>("resource", "grouped_light", id.value).single()
    }
}
