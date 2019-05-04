package inkapplications.shade

import inkapplications.shade.auth.TokenStorage
import java.util.concurrent.atomic.AtomicReference

/**
 * Stores Tokens in memory.
 *
 * This isn't an ideal way to store a token, as it will require
 * pushing the hub button to confirm every time the app is restarted.
 * But, it makes a decent default.
 */
object InMemoryStorage: TokenStorage {
    private val token = AtomicReference<String?>(null)

    override suspend fun getToken(): String? = token.get()
    override suspend fun setToken(token: String?) {
        this.token.set(token)
    }
}
