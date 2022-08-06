package inkapplications.shade.lights

import inkapplications.shade.lights.structures.Light
import inkapplications.shade.structures.ResourceId

/**
 * Actions to get and control lighting state.
 */
interface LightControls {
    /**
     * Get the state of a single light
     */
    suspend fun getLight(id: ResourceId): Light

    /**
     * Get a list of all lights connected to the bridge
     */
    suspend fun getLights(): List<Light>
}
