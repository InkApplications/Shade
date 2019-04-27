package inkapplications.shade.lights

import inkapplications.shade.auth.TokenStorage
import retrofit2.Retrofit

/**
 * Constructs lights services.
 */
class ShadeLightsModule {
    /**
     * Create a new instance of the lighting interace.
     *
     * @param retrofit Client to create hue requests with
     * @param tokenStorage A place to read/write the auth token used for requests.
     */
    fun createLights(retrofit: Retrofit, tokenStorage: TokenStorage): ShadeLights {
        val api = retrofit.create(HueLightsApi::class.java)

        return ApiLights(api, tokenStorage)
    }
}
