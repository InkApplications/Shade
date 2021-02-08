package inkapplications.shade.auth

import inkapplications.shade.constructs.ErrorCodes
import inkapplications.shade.constructs.ShadeApiError
import inkapplications.shade.constructs.ShadeException
import inkapplications.shade.constructs.throwOnFailure
import inkapplications.shade.serialization.parse
import kotlinx.coroutines.delay
import retrofit2.HttpException

/**
 * Error type returned by Hue Bridge for a non-existing endpoint.
 */
const val METHOD_NOT_AVAILABLE = 4

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

    /**
     * Validate token.
     */
    suspend fun validateToken(token: String): Boolean
}

/**
 * Implement Shade's auth API with Hue's bullshit.
 */
internal class ApiAuth(
    private val authApi: HueAuthApi,
    private val appId: String,
    private val storage: TokenStorage
) : ShadeAuth {
    override suspend fun awaitToken(retries: Int, timeout: Long) {
        repeat(retries) {
            try {
                DeviceType(appId)
                    .let { authApi.createToken(it) }
                    .token
                    .also { storage.setToken(it) }
                return
            } catch (error: ShadeApiError) {
                if (error.hueError.type != ErrorCodes.LINK_REQUIRED) throw error
                delay(timeout)
            } catch (error: HttpException) {
                throw error.parse()
            }
        }
        throw ShadeException("Auth timed out")
    }

    /**
     * Validate token.
     *
     * Send request to a non-existing endpoint to validate token based on error type:
     * Invalid token returns error type 1 (unauthorized user)
     * Valid token returns error type 4 (method not available)
     */
    override suspend fun validateToken(token: String): Boolean {
        if (token.isNotBlank()) {
            try {
                authApi.validateToken(token).throwOnFailure()
            } catch (error: ShadeApiError) {
                return error.hueError.type == METHOD_NOT_AVAILABLE
            } catch (error: HttpException) {
                throw error.parse()
            }
        }
        return false
    }
}
