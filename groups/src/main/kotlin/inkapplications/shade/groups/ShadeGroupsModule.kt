package inkapplications.shade.groups

import com.squareup.moshi.Moshi
import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.config.ShadeConfig
import inkapplications.shade.serialization.adapter.ShadeDeferredCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Constructs Groups services.
 */
class ShadeGroupsModule {
    /**
     * Create new instance of the Groups services.
     *
     * @param client Client to create hue requests with
     * @param config App-wide configuration for Shade, used to set up connections.
     * @param tokenStorage A place to read/write the auth token used for requests.
     */
    fun createGroups(client: OkHttpClient, config: ShadeConfig, tokenStorage: TokenStorage): ShadeGroups {
        val moshi = Moshi.Builder().build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(config.baseUrl)
            .addCallAdapterFactory(ShadeDeferredCallAdapterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        val api = retrofit.create(HueGroupsApi::class.java)

        return ApiGroups(api, tokenStorage)
    }
}
