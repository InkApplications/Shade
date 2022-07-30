package inkapplications.shade.internals

import inkapplications.shade.structures.SecurityStrategy
import io.ktor.client.engine.*
import io.ktor.client.engine.js.*

internal actual class PlatformModule {
    actual fun createEngine(securityStrategy: SecurityStrategy): HttpClientEngineFactory<*> {
        if (securityStrategy !is SecurityStrategy.PlatformTrust) {
            throw IllegalArgumentException("Javascript client cannot change security settings and must rely on the platform's trust.")
        }

        return Js
    }
}
