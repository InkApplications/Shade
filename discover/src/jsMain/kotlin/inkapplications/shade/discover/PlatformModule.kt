package inkapplications.shade.discover

import io.ktor.client.engine.*
import io.ktor.client.engine.js.*

internal actual class PlatformModule actual constructor() {
    actual fun createEngine(): HttpClientEngineFactory<*> = Js
}
