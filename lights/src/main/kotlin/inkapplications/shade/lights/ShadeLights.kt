package inkapplications.shade.lights

import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.auth.UnauthorizedException
import inkapplications.shade.constructs.Scan

/**
 * Actions to control lights.
 */
interface ShadeLights {
    /**
     * Gets a list of all lights that have been discovered by the bridge.
     */
    suspend fun getLights(): Map<String, Light>

    /**
     * Get lights that were discovered the last time a search was performed.
     *
     * The list of new lights is always deleted when a new search is started.
     */
    suspend fun getNewLights(): Scan

    /**
     * Allows the user to turn the light on and off, modify the hue and effects.
     *
     * @param id The ID (not the uuid) of the light to modify.
     * @param state Arguments to change about the light.
     */
    suspend fun setLightState(id: String, state: LightStateModification)
}

/**
 * API implementation of Shade's lights functionality.
 */
internal class ApiLights(
    private val lightsApi: HueLightsApi,
    private val storage: TokenStorage
): ShadeLights {
    private suspend fun getToken() = storage.getToken() ?: throw UnauthorizedException()

    override suspend fun getLights(): Map<String, Light> = lightsApi.getLights(getToken()).await()
    override suspend fun getNewLights(): Scan = lightsApi.getNewLights(getToken()).await()
    override suspend fun setLightState(id: String, state: LightStateModification) = lightsApi.setState(getToken(), id, state).await()
}
