package inkapplications.shade.schedules

import com.squareup.moshi.Moshi
import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.config.ShadeConfig
import inkapplications.shade.serialization.InstantDeserializer
import inkapplications.shade.serialization.LocalDateTimeDeserializer
import inkapplications.shade.serialization.adapter.ShadeDeferredCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Constructs Schedule Services
 */
class ShadeSchedulesModule {
    /**
     * Create a new instance of the schedules interface.
     *
     * @param client Client to create hue requests with
     * @param config App-wide configuration for Shade, used to set up connections.
     * @param tokenStorage A place to read/write the auth token used for requests.
     */
    fun createLights(client: OkHttpClient, config: ShadeConfig, tokenStorage: TokenStorage): ShadeSchedules {
        val moshi = Moshi.Builder()
            .add(InstantDeserializer)
            .add(LocalDateTimeDeserializer)
            .build()
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(config.baseUrl)
            .addCallAdapterFactory(ShadeDeferredCallAdapterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        val api = retrofit.create(HueSchedulesApi::class.java)

        return ApiSchedules(api, tokenStorage)
    }
}
