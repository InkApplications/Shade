package inkapplications.shade.lights

import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.auth.UnauthorizedException

/**
 * Actions to control lights.
 */
interface ShadeLights {
    /**
     * Gets a list of all lights that have been discovered by the bridge.
     */
    suspend fun getLights(): Map<String, Light>

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
    override suspend fun getLights(): Map<String, Light> {
        val token = storage.getToken() ?: throw UnauthorizedException()

        return lightsApi.getLights(token).await()
    }

    override suspend fun setLightState(id: String, state: LightStateModification) {
        val token = storage.getToken() ?: throw UnauthorizedException()

        lightsApi.setState(token, id, state).await()
    }
}
