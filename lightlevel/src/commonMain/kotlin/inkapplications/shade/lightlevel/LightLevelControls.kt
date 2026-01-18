package inkapplications.shade.lightlevel

import inkapplications.shade.lightlevel.parameters.LightLevelUpdateParameters
import inkapplications.shade.lightlevel.structures.LightLevel
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference

/**
 * Actions to get light level sensor data from the hue system.
 */
interface LightLevelControls {
    /**
     * Get a list of light level sensors configured on the hue service.
     *
     * @return A list of all light level sensors configured on the bridge.
     */
    suspend fun listLightLevels(): List<LightLevel>

    /**
     * Get the state of a single light level sensor.
     *
     * @param id The resource ID of the light level sensor to fetch data about.
     * @return State and capability information about the light level sensor.
     */
    suspend fun getLightLevel(id: ResourceId): LightLevel

    /**
     * Update a light level sensor on the hue bridge.
     *
     * @param id The resource ID of the light level sensor to be updated.
     * @param parameters Data about the light level sensor to be updated.
     * @return A reference to the updated resource.
     */
    suspend fun updateLightLevel(id: ResourceId, parameters: LightLevelUpdateParameters): ResourceReference
}
