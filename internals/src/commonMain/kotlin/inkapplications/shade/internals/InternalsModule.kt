package inkapplications.shade.internals

import inkapplications.shade.structures.ApplicationKey
import inkapplications.shade.structures.SecurityStrategy
import kimchi.logger.EmptyLogger
import kimchi.logger.KimchiLogger

/**
 * Provides access to services in the internals module.
 */
class InternalsModule(
    hostname: String? = null,
    applicationKey: ApplicationKey? = null,
    securityStrategy: SecurityStrategy = SecurityStrategy.PlatformTrust,
    logger: KimchiLogger = EmptyLogger,
) {
    private val platformModule = PlatformModule()
    private val configurableHttpClient = ConfigurableHttpClient(
        hostname = hostname,
        applicationKey = applicationKey,
        securityStrategy = securityStrategy,
        platformModule = platformModule,
        logger = logger,
    )

    /**
     * HttpClient initialized to be configured by the [configurationContainer].
     */
    val hueHttpClient: HueHttpClient = configurableHttpClient

    /**
     * Container for storing Host/Token information on the API..
     */
    val configurationContainer: HueConfigurationContainer = configurableHttpClient
}

