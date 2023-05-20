package inkapplications.shade.scenes

import inkapplications.shade.scenes.structures.Scene

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
}
