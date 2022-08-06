package inkapplications.shade.lights

import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.lights.structures.Light
import inkapplications.shade.structures.ResourceId

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
}
