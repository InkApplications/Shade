package inkapplications.shade.scenes

import inkapplications.shade.scenes.structures.Scene
import inkapplications.shade.structures.ResourceId

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
}
