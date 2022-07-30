package inkapplications.shade.internals

import inkapplications.shade.structures.ApplicationKey
import inkapplications.shade.structures.SecurityStrategy

/**
 * Provides access to services in the internals module.
 */
class InternalsModule(
    hostname: String? = null,
    applicationKey: ApplicationKey? = null,
    securityStrategy: SecurityStrategy = SecurityStrategy.PlatformTrust,
) {
    private val platformModule = PlatformModule()
    private val configurableHttpClient = ConfigurableHttpClient(
        hostname = hostname,
        applicationKey = applicationKey,
        securityStrategy = securityStrategy,
        platformModule = platformModule
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

