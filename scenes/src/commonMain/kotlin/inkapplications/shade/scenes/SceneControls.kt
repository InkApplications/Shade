package inkapplications.shade.scenes

import inkapplications.shade.scenes.parameters.SceneCreateParameters
import inkapplications.shade.scenes.parameters.SceneUpdateParameters
import inkapplications.shade.scenes.structures.Scene
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference

/**
 * Actions to get and configure Scenes on the Hue system.
 */
interface SceneControls {
    /**
     * Get a list of all scenes configured on the Hue Bridge.
     *
     * @return a list of all configured scenes on the Hue bridge.
     */
    suspend fun listScenes(): List<Scene>

    /**
     * Get information for a specific Scene.
     *
     * @param id The v2 resource ID of the Scene to get information for.
     * @return information about the scene
     */
    suspend fun getScene(id: ResourceId): Scene

    /**
     * Create a new scene on the Hue Bridge.
     *
     * @param parameters Configuration to define the new scene.
     * @return A reference to the newly created scene.
     */
    suspend fun createScene(parameters: SceneCreateParameters): ResourceReference

    /**
     * Update a scene on the Hue Bridge.
     *
     * @param id The v2 resource ID of the Scene to update.
     * @param parameters Configuration to define the new scene.
     * @return A reference to the updated scene.
     */
    suspend fun updateScene(id: ResourceId, parameters: SceneUpdateParameters): ResourceReference
}
