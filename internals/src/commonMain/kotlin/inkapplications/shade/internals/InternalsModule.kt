package inkapplications.shade.internals

import inkapplications.shade.structures.HueConfigurationContainer
import kimchi.logger.EmptyLogger
import kimchi.logger.KimchiLogger
import kotlinx.serialization.json.Json

/**
 * Provides access to services in the internals module.
 *
 * @param configurationContainer Container for storing Host/Token information on the API.
 * @param logger Logger used in network and internal operations
 */
class InternalsModule(
    val configurationContainer: HueConfigurationContainer,
    logger: KimchiLogger = EmptyLogger,
) {
    val json = Json {
        ignoreUnknownKeys = true
    }
    val platformModule = PlatformModule(configurationContainer, json, logger)
    private val configurableHttpClient = ConfigurableHttpClient(
        platformModule = platformModule,
        configurationContainer = configurationContainer,
        json = json,
        logger = logger,
    )

    /**
     * HttpClient initialized to be configured by the [configurationContainer].
     */
    val hueHttpClient: HueHttpClient = configurableHttpClient
}
