package inkapplications.shade.scenes

import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.auth.UnauthorizedException
import inkapplications.shade.serialization.encapsulateErrors

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

    /**
     * Create a new Scene for a group of lights
     *
     * @param name A displayable name for the Scene
     * @param group The ID of the group to associate with the Scene
     * @param picture It's never really explained how this works, but maybe a file name?
     * @param data Random app data you want to store with the scene.
     * @return The ID of the scene that was created.
     */
    suspend fun createGroupScene(
        name: String,
        group: String,
        picture: String? = null,
        data: Map<String, Any>? = null
    ): String

    /**
     * Get a single scene known to the Hue Bridge.
     *
     * @param id The Scene ID to look up.
     */
    suspend fun getScene(id: String): Scene

    /**
     * Remove a scene from the Hue Bridge
     *
     * @param id The ID of the Scene to be deleted.
     */
    suspend fun deleteScene(id: String)
}

/**
 * Internal API implementation of the Scenes interface.
 */
internal class ApiScenes(
    private val scenesApi: HueScenesApi,
    private val storage: TokenStorage
): ShadeScenes {
    private suspend fun getToken(): String = storage.getToken() ?: throw UnauthorizedException()

    override suspend fun getScenes(): Map<String, Scene> = encapsulateErrors {
        scenesApi.getScenes(getToken())
    }

    override suspend fun createLightScene(
        name: String,
        lights: List<String>,
        picture: String?,
        data: Map<String, Any>?
    ): String = encapsulateErrors {
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

    override suspend fun createGroupScene(
        name: String,
        group: String,
        picture: String?,
        data: Map<String, Any>?
    ): String = encapsulateErrors {
        val scene = CreateScene.GroupScene(
            name = name,
            group = group,
            picture = picture,
            data = data
        )
        return scene.let { scenesApi.createScene(getToken(), it) }
            .first()
            .success!!
            .id
    }

    override suspend fun getScene(id: String): Scene = encapsulateErrors {
        return scenesApi.getScene(getToken(), id)
    }

    override suspend fun deleteScene(id: String): Unit = encapsulateErrors {
        scenesApi.deleteScene(getToken(), id)
    }
}
