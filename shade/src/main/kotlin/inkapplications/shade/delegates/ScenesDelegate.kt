package inkapplications.shade.delegates

import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.scenes.Scene
import inkapplications.shade.scenes.ShadeScenes
import inkapplications.shade.scenes.ShadeScenesModule
import okhttp3.OkHttpClient

internal class ScenesDelegate(
    initialUrl: String?,
    private val client: OkHttpClient,
    private val storage: TokenStorage
): EndpointDelegate<ShadeScenes>(initialUrl), ShadeScenes {
    override fun createEndpoint(baseUrl: String): ShadeScenes {
        return ShadeScenesModule().createScenes(baseUrl, client, storage)
    }
    override suspend fun getScenes(): Map<String, Scene> = delegate.getScenes()

    override suspend fun createLightScene(
        name: String,
        lights: List<String>,
        picture: String?,
        data: Map<String, Any>?
    ): String = delegate.createLightScene(name, lights, picture, data)

    override suspend fun createGroupScene(
        name: String,
        group: String,
        picture: String?,
        data: Map<String, Any>?
    ): String = delegate.createGroupScene(name, group, picture, data)

    override suspend fun updateLightScene(
        id: String,
        name: String?,
        lights: List<String>?,
        picture: String?,
        data: Map<String, Any>?
    ) = delegate.updateLightScene(id, name, lights, picture, data)

    override suspend fun updateGroupScene(
        id: String,
        name: String?,
        picture: String?,
        data: Map<String, Any>?
    ) = delegate.updateGroupScene(id, name, picture, data)

    override suspend fun getScene(id: String): Scene = delegate.getScene(id)

    override suspend fun deleteScene(id: String) = delegate.deleteScene(id)
}
