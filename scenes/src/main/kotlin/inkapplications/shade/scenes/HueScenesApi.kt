package inkapplications.shade.scenes

import retrofit2.http.GET

internal interface HueScenesApi {
    @GET("api/{token}/scenes")
    suspend fun getScenes()
}
