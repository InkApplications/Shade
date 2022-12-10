package inkapplications.shade.discover

import io.ktor.client.engine.*
import io.ktor.client.engine.winhttp.*

internal actual class PlatformModule actual constructor() {
    actual fun createEngine(): HttpClientEngineFactory<*> = WinHttp
}
