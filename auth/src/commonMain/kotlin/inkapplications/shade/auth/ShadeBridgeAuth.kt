package inkapplications.shade.auth

import inkapplications.shade.auth.structures.AppId
import inkapplications.shade.auth.structures.AuthRequest
import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.structures.ApiError
import inkapplications.shade.structures.AuthToken
import inkapplications.shade.structures.AuthorizationTimeoutException
import inkapplications.shade.structures.HueConfigurationContainer
import kimchi.logger.KimchiLogger
import kotlinx.coroutines.delay
import kotlinx.serialization.serializer
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

/**
 * Implements bridge auth with the hue client
 */
internal class ShadeBridgeAuth(
    private val client: HueHttpClient,
    private val configurationContainer: HueConfigurationContainer,
    private val logger: KimchiLogger,
): BridgeAuth {
    @ExperimentalTime
    override suspend fun awaitToken(
        appId: AppId,
        retries: Int,
        timeout: Duration,
    ): AuthToken {
        val authRequest = AuthRequest(
            appId = appId,
            generateClientKey = true,
        )

        repeat(retries) {
            try {
                val authToken: AuthToken = client.sendV1Request(
                    method = "POST",
                    body = authRequest,
                    pathSegments = emptyArray(),
                    requestSerializer = serializer(),
                    responseSerializer = serializer(),
                )

                configurationContainer.setAuthToken(authToken)

                return authToken
            } catch (e: ApiError) {
                if (e.code == 200) {
                    logger.debug("Received Expected API Error, Waiting $timeout before retry", e)
                    delay(timeout)
                } else {
                    throw e
                }
            }
        }

        throw AuthorizationTimeoutException
    }
}
