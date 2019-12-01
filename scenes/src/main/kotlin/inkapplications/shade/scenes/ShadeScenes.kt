package inkapplications.shade.scenes

import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.auth.UnauthorizedException

/**
 * Access to Hue's scene storage.
 */
interface ShadeScenes {
    /**
     * Get a list of all scenes known to the Hue Bridge.
     */
    suspend fun getScenes(): Map<String, Scene>

    /**
     * Create a new Scene for specific lights
     *
     * @param name A displayable name for the Scene
     * @param lights The ID's of the lights to associate with the Scene
     * @param picture It's never really explained how this works, but maybe a file name?
     * @param data Random app data you want to store with the scene.
     * @return The ID of the scene that was created.
     */
    suspend fun createLightScene(
        name: String,
        lights: List<String>,
        picture: String? = null,
        data: Map<String, Any>? = null
    ): String
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

    override suspend fun createLightScene(
        name: String,
        lights: List<String>,
        picture: String?,
        data: Map<String, Any>?
    ): String {
        val scene = CreateScene.LightScene(
            name = name,
            lights = lights,
            picture = picture,
            data = data
        )
        return scene.let { scenesApi.createScene(getToken(), it) }
            .first()
            .success!!
            .id
    }
}
