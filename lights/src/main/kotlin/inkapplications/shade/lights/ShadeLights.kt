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
}
