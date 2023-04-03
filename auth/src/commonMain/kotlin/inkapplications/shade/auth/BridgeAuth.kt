package inkapplications.shade.auth

import inkapplications.shade.auth.structures.AppId
import inkapplications.shade.structures.AuthToken
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

/**
 * Handles retrieving a token for authorization with the Hue Bridge.
 */
interface BridgeAuth {
    /**
     * Wait for the user to hit the confirmation button to get a token.
     *
     * @param retries How many times to ask the hue bridge for a token
     *        before giving up and timing out. (Default 50)
     * @param timeout The amount of time to wait in-between requests. (Default 5 seconds)
     * @return A bearer token to be used with requests to the Hue API.
     *         These do not appear to expire. Store it safely.
     */
    @ExperimentalTime
    suspend fun awaitToken(
        appId: AppId,
        retries: Int = 50,
        timeout: Duration = 5.seconds
    ): AuthToken
}

