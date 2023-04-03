package inkapplications.shade.discover

import io.ktor.client.engine.*

/**
 * Provides platform-specific dependencies for the SDK.
 */
internal expect class PlatformModule() {
    /**
     * Create Ktor http engine based on the platform.
     */
    fun createEngine(): HttpClientEngineFactory<*>
}
