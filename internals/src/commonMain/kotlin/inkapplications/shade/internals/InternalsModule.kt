package inkapplications.shade.internals

/**
 * Provides access to services in the internals module.
 */
class InternalsModule {
    private val platformModule = PlatformModule()
    private val configurableHttpClient = ConfigurableHttpClient(platformModule)

    /**
     * HttpClient initialized to be configured by the [configurationContainer].
     */
    val hueHttpClient: HueHttpClient = configurableHttpClient

    /**
     * Container for storing Host/Token information on the API..
     */
    val configurationContainer: HueConfigurationContainer = configurableHttpClient
}

