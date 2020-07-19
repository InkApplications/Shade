package inkapplications.shade.lights

import com.squareup.moshi.Moshi
import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.serialization.*
import inkapplications.shade.serialization.converter.FirstInCollectionConverterFactory
import inkapplications.shade.serialization.converter.UnitConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import shade.http.RateLimitInterceptor

/**
 * Constructs lights services.
 */
class ShadeLightsModule {
    fun transformers(): Set<Any> = setOf(
        CoordinatesListTransformer,
        InstantTransformer,
        ColorTemperatureTransformer,
        TemperatureRangeTransformer,
        BrightnessTransformer,
        DurationTransformer,
        HueLightStateTransformer
    )

    /**
     * Create a new instance of the lighting interface.
     *
     * @param client Client to create hue requests with
     * @param baseUrl URL of the Hue Bridge API
     * @param tokenStorage A place to read/write the auth token used for requests.
     */
    fun createLights(baseUrl: String, client: OkHttpClient, tokenStorage: TokenStorage): ShadeLights {
        val apiClient = client.newBuilder()
            .addInterceptor(RateLimitInterceptor)
            .build()
        val moshi = Moshi.Builder()
            .add(ScanAdapter)
            .apply { transformers().forEach { add(it) } }
            .build()
        val retrofit = Retrofit.Builder()
            .client(apiClient)
            .baseUrl(baseUrl)
            .addConverterFactory(UnitConverterFactory)
            .addConverterFactory(FirstInCollectionConverterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        val api = retrofit.create(HueLightsApi::class.java)

        return ApiLights(api, tokenStorage)
    }
}
