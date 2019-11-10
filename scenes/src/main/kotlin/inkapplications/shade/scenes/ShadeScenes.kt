package inkapplications.shade.scenes

import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.auth.UnauthorizedException

/**
 * Access to Hue's scene storage.
 */
interface ShadeScenes {
    suspend fun getScenes(): Map<String, Scene>
}

/**
 * Internal API implementation of the Scenes interface.
 */
internal class ApiScenes(
    private val scenesApi: HueScenesApi,
    private val storage: TokenStorage
): ShadeScenes {
    private suspend fun getToken(): String = storage.getToken() ?: throw UnauthorizedException()

    override suspend fun getScenes(): Map<String, Scene> {
        return scenesApi.getScenes(getToken())
    }
}
