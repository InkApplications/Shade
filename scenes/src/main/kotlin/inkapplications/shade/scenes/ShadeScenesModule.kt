package inkapplications.shade.scenes

import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.config.ShadeConfig
import okhttp3.OkHttpClient

/**
 * Constructs scenes services.
 */
class ShadeScenesModule {
    /**
     * Create new instance of the Scenes service.
     *
     * @param client Client to create hue requests with
     * @param config App-wide configuration for Shade, used to set up connections.
     * @param tokenStorage A place to read/write the auth token used for requests.
     */
    fun createGroups(client: OkHttpClient, config: ShadeConfig, tokenStorage: TokenStorage): ShadeScenes {
        return ApiScenes()
    }
}
