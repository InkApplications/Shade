package inkapplications.shade.internals

import inkapplications.shade.structures.HueConfigurationContainer
import inkapplications.shade.structures.SecurityStrategy
import io.ktor.client.engine.*
import io.ktor.client.engine.curl.*
import kimchi.logger.KimchiLogger
import kotlinx.serialization.json.Json

actual class PlatformModule actual constructor(
    configurationContainer: HueConfigurationContainer,
    json: Json,
    logger: KimchiLogger
) {
    actual fun createEngine(securityStrategy: SecurityStrategy): HttpClientEngineFactory<*> {
        if (securityStrategy !is SecurityStrategy.PlatformTrust) {
            throw IllegalArgumentException("Native client cannot change security settings and must rely on the platform's trust.")
        }

        return Curl
    }
}
