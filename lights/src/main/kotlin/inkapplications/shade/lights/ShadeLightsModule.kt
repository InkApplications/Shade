package inkapplications.shade.lights

import com.squareup.moshi.Moshi
import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.config.ShadeConfig
import inkapplications.shade.serialization.CoordinatesListDeserializer
import inkapplications.shade.serialization.InstantDeserializer
import inkapplications.shade.serialization.ScanAdapter
import inkapplications.shade.serialization.RangeDeserializer
import inkapplications.shade.serialization.converter.FirstInCollectionConverterFactory
import inkapplications.shade.serialization.converter.UnitConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Constructs lights services.
 */
class ShadeLightsModule {
    /**
     * Create a new instance of the lighting interface.
     *
     * @param client Client to create hue requests with
     * @param config App-wide configuration for Shade, used to set up connections.
     * @param tokenStorage A place to read/write the auth token used for requests.
     */
    fun createLights(client: OkHttpClient, config: ShadeConfig, tokenStorage: TokenStorage): ShadeLights {
        val moshi = Moshi.Builder()
            .add(CoordinatesListDeserializer)
            .add(InstantDeserializer)
            .add(ScanAdapter)
            .add(RangeDeserializer)
            .build()
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(config.baseUrl)
            .addConverterFactory(UnitConverterFactory)
            .addConverterFactory(FirstInCollectionConverterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        val api = retrofit.create(HueLightsApi::class.java)

        return ApiLights(api, tokenStorage)
    }
}
