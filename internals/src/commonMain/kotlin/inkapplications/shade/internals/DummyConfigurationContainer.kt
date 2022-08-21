package inkapplications.shade.internals

import inkapplications.shade.structures.AuthToken
import inkapplications.shade.structures.SecurityStrategy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * An empty implementation of the configuration container.
 *
 * This can be useful either for testing or for scenarios where you do not
 * want the configurations to be saved.
 */
object DummyConfigurationContainer: HueConfigurationContainer {
    override val hostname: StateFlow<String?> = MutableStateFlow(null)
    override val securityStrategy: StateFlow<SecurityStrategy> = MutableStateFlow(SecurityStrategy.PlatformTrust)
    override val authToken: StateFlow<AuthToken?> = MutableStateFlow(null)
    override suspend fun setHostname(hostname: String?) = Unit
    override suspend fun setAuthToken(key: AuthToken?) = Unit
    override suspend fun setSecurityStrategy(securityStrategy: SecurityStrategy) = Unit
}
