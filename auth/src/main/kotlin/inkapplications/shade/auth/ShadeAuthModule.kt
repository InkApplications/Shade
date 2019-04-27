package inkapplications.shade.auth

import com.squareup.moshi.Moshi
import inkapplications.shade.config.ShadeConfig
import inkapplications.shade.serialization.adapter.ShadeDeferredCallAdapterFactory
import inkapplications.shade.serialization.converter.FirstInCollectionConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Constructs Auth resources.
 */
class ShadeAuthModule {
    /**
     * Create a new instance of Shade's Auth Interface.
     */
    fun createAuth(client: OkHttpClient, config: ShadeConfig, tokenStorage: TokenStorage): ShadeAuth {
        val moshi = Moshi.Builder().build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(config.baseUrl)
            .addCallAdapterFactory(ShadeDeferredCallAdapterFactory)
            .addConverterFactory(FirstInCollectionConverterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val api = retrofit.create(HueAuthApi::class.java)

        return ApiAuth(api, config, tokenStorage)
    }
}
