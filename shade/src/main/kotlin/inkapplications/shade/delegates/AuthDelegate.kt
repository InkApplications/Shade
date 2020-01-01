package inkapplications.shade.delegates

import inkapplications.shade.auth.ShadeAuth
import inkapplications.shade.auth.ShadeAuthModule
import inkapplications.shade.auth.TokenStorage
import okhttp3.OkHttpClient

internal class AuthDelegate(
    initialUrl: String?,
    private val appId: String,
    private val client: OkHttpClient,
    private val storage: TokenStorage
): EndpointDelegate<ShadeAuth>(initialUrl), ShadeAuth {
    override fun createEndpoint(baseUrl: String): ShadeAuth {
        return ShadeAuthModule().createAuth(baseUrl, appId, client, storage)
    }

    override suspend fun awaitToken(retries: Int, timeout: Long) = delegate.awaitToken(retries, timeout)
}
