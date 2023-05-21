package inkapplications.shade.scenes

import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.internals.postData
import inkapplications.shade.scenes.parameters.SceneCreateParameters
import inkapplications.shade.scenes.structures.Scene
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference

internal class ShadeScenes(
    private val hueClient: HueHttpClient,
): SceneControls {
    override suspend fun listScenes(): List<Scene> {
        return hueClient.getData("resource", "scene")
    }

    override suspend fun getScene(id: ResourceId): Scene {
        return hueClient.getData<List<Scene>>("resource", "scene", id.value).single()
    }

    override suspend fun createScene(parameters: SceneCreateParameters): ResourceReference {
        val response: List<ResourceReference> = hueClient.postData(
            body = parameters,
            pathSegments = arrayOf("resource", "scene"),
        )

        return response.single()
    }
}
