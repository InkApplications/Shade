package inkapplications.shade.lights

import inkapplications.shade.internals.*
import inkapplications.shade.structures.ResourceId

class ShadeLightsModule(
    internalsModule: InternalsModule,
) {
    val lights: LightControls = ShadeLights(internalsModule.hueHttpClient)
}

interface LightControls {
    suspend fun getLight(id: ResourceId): Light
}

internal class ShadeLights(
    private val hueClient: HueHttpClient,
): LightControls {
    override suspend fun getLight(id: ResourceId): Light {
        return hueClient.getData<List<Light>>("resource", "light", id.value).single()
    }
}
