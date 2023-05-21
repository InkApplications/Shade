package inkapplications.shade.scenes

import inkapplications.shade.internals.*
import inkapplications.shade.scenes.parameters.SceneCreateParameters
import inkapplications.shade.scenes.parameters.SceneUpdateParameters
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

    override suspend fun updateScene(id: ResourceId, parameters: SceneUpdateParameters): ResourceReference {
        val response: List<ResourceReference> = hueClient.putData(
            body = parameters,
            pathSegments = arrayOf("resource", "scene", id.value),
        )

        return response.single()
    }

    override suspend fun deleteScene(id: ResourceId): ResourceReference {
        val response = hueClient.deleteData<List<ResourceReference>>("resource", "scene", id.value)

        return response.single()
    }
}
