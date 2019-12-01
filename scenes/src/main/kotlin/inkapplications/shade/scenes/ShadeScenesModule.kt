package inkapplications.shade.scenes

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import inkapplications.shade.auth.TokenStorage
import inkapplications.shade.config.ShadeConfig
import inkapplications.shade.serialization.InstantDeserializer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Constructs scenes services.
 */
class ShadeScenesModule {
    /**
     * Create new instance of the Scenes service.
     *
     * @param client Client to create hue requests with
     * @param config App-wide configuration for Shade, used to set up connections.
     * @param tokenStorage A place to read/write the auth token used for requests.
     */
    fun createGroups(client: OkHttpClient, config: ShadeConfig, tokenStorage: TokenStorage): ShadeScenes {
        val moshi = Moshi.Builder()
            .add(
                PolymorphicJsonAdapterFactory.of(Scene::class.java, "type")
                    .withSubtype(Scene.GroupScene::class.java, "GroupScene")
                    .withSubtype(Scene.LightScene::class.java, "LightScene")
            )
            .add(
                PolymorphicJsonAdapterFactory.of(CreateScene::class.java, "type")
                    .withSubtype(CreateScene.GroupScene::class.java, "GroupScene")
                    .withSubtype(CreateScene.LightScene::class.java, "LightScene")
            )
            .add(InstantDeserializer)
            .build()
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(config.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        val api = retrofit.create(HueScenesApi::class.java)

        return ApiScenes(api, tokenStorage)
    }
}
