package inkapplications.shade.lights

import inkapplications.shade.lights.parameters.LightUpdateParameters
import inkapplications.shade.lights.structures.Light
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference

/**
 * Actions to get and control lighting state.
 */
interface LightControls {
    /**
     * Get the state of a single light
     *
     * @param id The v2 unique ID of the light to fetch information for.
     */
    suspend fun getLight(id: ResourceId): Light

    /**
     * Get a list of all lights connected to the bridge
     */
    suspend fun listLights(): List<Light>

    /**
     * Update a light's state.
     *
     * @param id The v2 unique ID of the light to fetch information for.
     * @param parameters State to be changed on the light.
     * @return A reference to the updated resource.
     */
    suspend fun updateLight(id: ResourceId, parameters: LightUpdateParameters): ResourceReference
}
