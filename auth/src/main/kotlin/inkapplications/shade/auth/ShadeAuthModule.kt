package inkapplications.shade.auth

import inkapplications.shade.config.ShadeConfig
import retrofit2.Retrofit

/**
 * Constructs Auth resources.
 */
class ShadeAuthModule {
    /**
     * Create a new instance of Shade's Auth Interface.
     *
     * @param retrofit Client to create hue requests with
     * @param config App-wide configuration
     */
    fun createAuth(retrofit: Retrofit, config: ShadeConfig, tokenStorage: TokenStorage): ShadeAuth {
        val api = retrofit.create(HueAuthApi::class.java)

        return ApiAuth(api, config, tokenStorage)
    }
}
