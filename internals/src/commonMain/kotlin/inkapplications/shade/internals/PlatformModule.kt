package inkapplications.shade.internals

import inkapplications.shade.structures.HueConfigurationContainer
import inkapplications.shade.structures.SecurityStrategy
import io.ktor.client.engine.*
import kimchi.logger.EmptyLogger
import kimchi.logger.KimchiLogger
import kotlinx.serialization.json.Json

/**
 * Provides platform-specific dependencies for the SDK.
 */
expect class PlatformModule(
    configurationContainer: HueConfigurationContainer,
    json: Json,
    logger: KimchiLogger = EmptyLogger,
) {
    /**
     * Create Ktor http engine based on the platform.
     */
    fun createEngine(securityStrategy: SecurityStrategy): HttpClientEngineFactory<*>
}
