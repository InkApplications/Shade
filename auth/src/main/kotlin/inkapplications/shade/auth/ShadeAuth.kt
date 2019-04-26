package inkapplications.shade.auth

import inkapplications.shade.config.ShadeConfig
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
    suspend fun awaitToken(retries: Int = 50, timeout: Long = 5000): String
}

/**
 * Implement Shade's auth API with Hue's bullshit.
 */
internal class ApiAuth(
    private val authApi: HueAuthApi,
    private val config: ShadeConfig
): ShadeAuth {
    override suspend fun awaitToken(retries: Int, timeout: Long): String {
        repeat(retries) {
            try {
                return DeviceType(config.appId)
                    .let(authApi::createToken)
                    .await()
                    .token
            } catch (error: ShadeApiError) {
                if (error.hueError.type != 101) throw error
                delay(timeout)
            }
        }
        throw ShadeException("Auth timed out")
    }
}
