package inkapplications.shade.auth

import inkapplications.shade.config.ShadeConfig
import inkapplications.shade.hueclient.ErrorCodes
import inkapplications.shade.hueclient.ShadeApiError
import inkapplications.shade.hueclient.ShadeException
import kotlinx.coroutines.delay

/**
 * Authentication for the Phillips Hue bridge.
 */
interface ShadeAuth {
    /**
     * Wait for the user to hit the confirmation button to get a token.
     *
     * @param retries How many times to ask the hue bridge for a token
     *        before giving up and timing out.
     * @param timeout The amount of time to wait inbetween requests.
     * @return A bearer token to be used with requests to the Hue API.
     *         These do not appear to expire. Store it safely.
     */
    suspend fun awaitToken(retries: Int = 50, timeout: Long = 5000)
}

/**
 * Implement Shade's auth API with Hue's bullshit.
 */
internal class ApiAuth(
    private val authApi: HueAuthApi,
    private val config: ShadeConfig,
    private val storage: TokenStorage
): ShadeAuth {
    override suspend fun awaitToken(retries: Int, timeout: Long) {
        repeat(retries) {
            try {
                DeviceType(config.appId)
                    .let(authApi::createToken)
                    .await()
                    .token
                    .also { storage.setToken(it) }
                return
            } catch (error: ShadeApiError) {
                if (error.hueError.type != ErrorCodes.LINK_REQUIRED) throw error
                delay(timeout)
            }
        }
        throw ShadeException("Auth timed out")
    }
}
