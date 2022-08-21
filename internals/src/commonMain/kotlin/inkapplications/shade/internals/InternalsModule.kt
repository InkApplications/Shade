package inkapplications.shade.internals

import kimchi.logger.EmptyLogger
import kimchi.logger.KimchiLogger

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
    private val platformModule = PlatformModule()
    private val configurableHttpClient = ConfigurableHttpClient(
        platformModule = platformModule,
        configurationContainer = configurationContainer,
        logger = logger,
    )

    /**
     * HttpClient initialized to be configured by the [configurationContainer].
     */
    val hueHttpClient: HueHttpClient = configurableHttpClient
}

