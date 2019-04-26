package inkapplications.shade.hueclient

import com.squareup.moshi.Moshi
import inkapplications.shade.config.ShadeConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Create instances of the Shade network clients.
 *
 * @todo Can we hide this implementation detail?
 */
class HueClientModule {
    private val moshi = Moshi.Builder().build()

    fun retrofit(config: ShadeConfig, client: OkHttpClient) = Retrofit.Builder()
        .client(client)
        .baseUrl(config.baseUrl)
        .addCallAdapterFactory(DeferredCallAdapterFactory)
        .addConverterFactory(HueConverterFactory)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}
