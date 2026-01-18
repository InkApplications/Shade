package inkapplications.shade.lightlevel

import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.internals.putData
import inkapplications.shade.lightlevel.parameters.LightLevelUpdateParameters
import inkapplications.shade.lightlevel.structures.LightLevel
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference

/**
 * Implements light level sensor controls via the hue client.
 */
internal class ShadeLightLevel(
    private val hueHttpClient: HueHttpClient,
): LightLevelControls {
    override suspend fun listLightLevels(): List<LightLevel> {
        return hueHttpClient.getData("resource", "light_level")
    }

    override suspend fun getLightLevel(id: ResourceId): LightLevel {
        return hueHttpClient.getData<List<LightLevel>>("resource", "light_level", id.value).single()
    }

    override suspend fun updateLightLevel(
        id: ResourceId,
        parameters: LightLevelUpdateParameters
    ): ResourceReference {
        val response: List<ResourceReference> = hueHttpClient.putData(
            body = parameters,
            pathSegments = arrayOf("resource", "light_level", id.value),
        )

        return response.single()
    }
}
