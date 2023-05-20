package inkapplications.shade.scenes

import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.scenes.structures.Scene

internal class ShadeScenes(
    private val hueClient: HueHttpClient,
): SceneControls {
    override suspend fun listScenes(): List<Scene> {
        return hueClient.getData("resource", "scene")
    }
}
