package inkapplications.shade.discover

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/**
 * Provides access to Hue's discovery services.
 */
class DiscoverModule {
    private val platformModule = PlatformModule()

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val client = HttpClient(platformModule.createEngine()) {
        install(ContentNegotiation) {
            json(json)
        }
    }

    /**
     * Discovery implementation using Hue's online discovery protocol.
     *
     * Note that this implementation requires an active internet connection
     * on both the client and bridge device to function.
     */
    val onlineDiscovery: BridgeDiscovery = KtorDiscovery(client)
}
