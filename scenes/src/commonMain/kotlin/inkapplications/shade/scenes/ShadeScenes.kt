package inkapplications.shade.scenes

import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.scenes.structures.Scene
import inkapplications.shade.structures.ResourceId

internal class ShadeScenes(
    private val hueClient: HueHttpClient,
): SceneControls {
    override suspend fun listScenes(): List<Scene> {
        return hueClient.getData("resource", "scene")
    }

    override suspend fun getScene(id: ResourceId): Scene {
        return hueClient.getData<List<Scene>>("resource", "scene", id.value).single()
    }
}
