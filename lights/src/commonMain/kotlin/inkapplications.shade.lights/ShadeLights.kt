package inkapplications.shade.lights

import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.internals.putData
import inkapplications.shade.lights.parameters.LightUpdateParameters
import inkapplications.shade.lights.structures.Light
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference

/**
 * Implements lighting controls via the hue client
 */
internal class ShadeLights(
    private val hueClient: HueHttpClient,
): LightControls {
    override suspend fun getLight(id: ResourceId): Light {
        return hueClient.getData<List<Light>>("resource", "light", id.value).single()
    }

    override suspend fun getLights(): List<Light> {
        return hueClient.getData("resource", "light")
    }

    override suspend fun updateLight(id: ResourceId, parameters: LightUpdateParameters): ResourceReference {
        val response: List<ResourceReference> = hueClient.putData(
            body = parameters,
            pathSegments = arrayOf("resource", "light", id.value),
        )

        return response.single()
    }
}
