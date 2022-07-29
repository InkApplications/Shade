package inkapplications.shade.internals

import inkapplications.shade.structures.ApplicationKey

/**
 * Store configuration for communicating with the Hue API.
 */
interface HueConfigurationContainer {
    /**
     * Set the host of the hue bridge to communicate with.
     */
    fun setHost(hostname: String, securityStrategy: SecurityStrategy = SecurityStrategy.PlatformTrust)

    /**
     * Set the application key/token to use when communicating with the Hue bridge.
     */
    fun setApplicationKey(key: ApplicationKey)
}
