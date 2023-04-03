@file:OptIn(ExperimentalCoroutinesApi::class)

package inkapplications.shade.auth

import inkapplications.shade.auth.structures.AppId
import inkapplications.shade.internals.DummyConfigurationContainer
import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.HueStubClient
import inkapplications.shade.serialization.V1HueResponse
import inkapplications.shade.structures.ApiError
import inkapplications.shade.structures.AuthorizationTimeoutException
import kimchi.logger.EmptyLogger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.KSerializer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class ShadeBridgeAuthTest {
    @Test
    fun testRetries() = runTest {
        val spyClient = object : HueHttpClient by HueStubClient {
            var v1Requests = 0
            override suspend fun <REQUEST, RESPONSE> sendV1Request(
                method: String,
                pathSegments: Array<out String>,
                responseSerializer: KSerializer<List<V1HueResponse<RESPONSE>>>,
                body: REQUEST?,
                requestSerializer: KSerializer<REQUEST>?
            ): RESPONSE {
                v1Requests++

                throw ApiError(
                    code = 200,
                    errors = emptyList(),
                )
            }
        }
        val auth = ShadeBridgeAuth(
            client = spyClient,
            configurationContainer = DummyConfigurationContainer,
            logger = EmptyLogger,
        )

        val result = runCatching { auth.awaitToken(AppId("Test", "App"), retries = 5, timeout = 0.seconds) }

        assertTrue(result.isFailure)
        assertEquals(AuthorizationTimeoutException, result.exceptionOrNull())
        assertEquals(5, spyClient.v1Requests, "Auth retries the specified amount of times")
    }

    @Test
    fun retryTimeout() = runTest {
        val spyClient = object : HueHttpClient by HueStubClient {
            var v1Requests = 0
            override suspend fun <REQUEST, RESPONSE> sendV1Request(
                method: String,
                pathSegments: Array<out String>,
                responseSerializer: KSerializer<List<V1HueResponse<RESPONSE>>>,
                body: REQUEST?,
                requestSerializer: KSerializer<REQUEST>?
            ): RESPONSE {
                v1Requests++

                throw ApiError(
                    code = 200,
                    errors = emptyList(),
                )
            }
        }
        val auth = ShadeBridgeAuth(
            client = spyClient,
            configurationContainer = DummyConfigurationContainer,
            logger = EmptyLogger,
        )

        val result = async { runCatching { auth.awaitToken(AppId("Test", "App"), retries = 2, timeout = 1.seconds) } }
        assertEquals(0, spyClient.v1Requests)
        advanceTimeBy(1.seconds.inWholeMilliseconds)
        assertEquals(1, spyClient.v1Requests)
        advanceTimeBy(1.seconds.inWholeMilliseconds)
        assertEquals(2, spyClient.v1Requests)
        assertTrue(result.await().isFailure)
    }
}
