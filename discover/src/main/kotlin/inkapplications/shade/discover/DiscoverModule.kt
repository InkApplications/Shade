package inkapplications.shade.discover

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Constructs Discovery services.
 */
class DiscoverModule {
    fun createDiscoverclient(
        client: OkHttpClient,
        apiUrl: String = "https://www.meethue.com/api/"
    ): BridgeDiscovery {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(apiUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BridgeDiscovery::class.java)
    }
}
