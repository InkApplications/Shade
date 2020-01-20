package inkapplications.shade.schedules

import com.squareup.moshi.Moshi
import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.serialization.InstantTransformer
import inkapplications.shade.serialization.TimePatternTransformer
import inkapplications.shade.serialization.converter.FirstInCollectionConverterFactory
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
     * @param baseUrl URL of the Hue Bridge API
     * @param client Client to create hue requests with
     * @param tokenStorage A place to read/write the auth token used for requests.
     */
    fun createSchedule(baseUrl: String, client: OkHttpClient, tokenStorage: TokenStorage): ShadeSchedules {
        val moshi = Moshi.Builder()
            .add(TimePatternTransformer)
            .add(InstantTransformer)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(FirstInCollectionConverterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        val api = retrofit.create(HueSchedulesApi::class.java)

        return ApiSchedules(api, tokenStorage)
    }
}
