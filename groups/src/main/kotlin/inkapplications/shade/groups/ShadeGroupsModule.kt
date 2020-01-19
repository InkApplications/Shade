package inkapplications.shade.groups

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.serialization.ColorTemperatureDeserializer
import inkapplications.shade.serialization.CoordinatesListDeserializer
import inkapplications.shade.serialization.converter.FirstInCollectionConverterFactory
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
     * @param baseUrl URL of the Hue Bridge API
     * @param client Client to create hue requests with
     * @param tokenStorage A place to read/write the auth token used for requests.
     */
    fun createGroups(baseUrl: String, client: OkHttpClient, tokenStorage: TokenStorage): ShadeGroups {
        val moshi = Moshi.Builder()
            .add(
                PolymorphicJsonAdapterFactory.of(Group::class.java, "type")
                    .withSubtype(Group.Room::class.java, "Room")
                    .withSubtype(Group.Luminaire::class.java, "Luminaire")
                    .withSubtype(Group.Lightsource::class.java, "Lightsource")
                    .withSubtype(Group.LightGroup::class.java, "LightGroup")
                    .withSubtype(Group.Entertainment::class.java, "Entertainment")
                    .withSubtype(Group.Zone::class.java, "Zone")
            )
            .add(
                PolymorphicJsonAdapterFactory.of(MutableGroupAttributes::class.java, "type")
                    .withSubtype(MutableGroupAttributes.Room::class.java, "Room")
                    .withSubtype(MutableGroupAttributes.Luminaire::class.java, "Luminaire")
                    .withSubtype(MutableGroupAttributes.Lightsource::class.java, "Lightsource")
                    .withSubtype(MutableGroupAttributes.LightGroup::class.java, "LightGroup")
                    .withSubtype(MutableGroupAttributes.Entertainment::class.java, "Entertainment")
                    .withSubtype(MutableGroupAttributes.Zone::class.java, "Zone")
            )
            .add(ColorTemperatureDeserializer)
            .add(CoordinatesListDeserializer)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(FirstInCollectionConverterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        val api = retrofit.create(HueGroupsApi::class.java)

        return ApiGroups(api, tokenStorage)
    }
}
