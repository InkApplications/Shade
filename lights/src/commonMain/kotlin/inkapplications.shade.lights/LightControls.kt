package inkapplications.shade.lights

import inkapplications.shade.structures.ResourceId

/**
 * Actions to get and control lighting state.
 */
interface LightControls {
    /**
     * Get the state of a single light
     */
    suspend fun getLight(id: ResourceId): Light
}
