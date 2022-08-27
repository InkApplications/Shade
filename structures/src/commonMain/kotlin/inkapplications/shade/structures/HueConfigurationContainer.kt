package inkapplications.shade.structures

import inkapplications.shade.structures.AuthToken
import inkapplications.shade.structures.SecurityStrategy
import kotlinx.coroutines.flow.StateFlow

/**
 * Store configuration for communicating with the Hue API.
 */
interface HueConfigurationContainer {
    /**
     * The Hue IP or hostname to communicate with.
     */
    val hostname: StateFlow<String?>

    /**
     * TLS configuration strategy.
     */
    val securityStrategy: StateFlow<SecurityStrategy>

    /**
     * Auth token used in authenticated requests to the hue bridge
     */
    val authToken: StateFlow<AuthToken?>

    /**
     * he Hue IP or hostname to communicate with. eg "192.168.1.5"
     */
    suspend fun setHostname(hostname: String?)

    /**
     * Set the application key/token to use when communicating with the Hue bridge.
     */
    suspend fun setAuthToken(token: AuthToken?)

    /**
     * TLS configuration strategy.
     */
    suspend fun setSecurityStrategy(securityStrategy: SecurityStrategy)
}
