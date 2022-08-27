package inkapplications.shade.structures

import inkapplications.shade.structures.AuthToken
import inkapplications.shade.structures.HueConfigurationContainer
import inkapplications.shade.structures.SecurityStrategy
import kotlinx.coroutines.flow.*

/**
 * Implements a configuration container by storing config in a thread-safe
 * memory storage structure.
 *
 * @param initialAuthToken Optional auth token to start with
 */
class InMemoryConfigurationContainer(
    initialHostname: String? = null,
    initialAuthToken: AuthToken? = null,
    initialSecurityStrategy: SecurityStrategy = SecurityStrategy.PlatformTrust,
): HueConfigurationContainer {
    private val mutableHostname = MutableStateFlow(initialHostname)
    private val mutableAuthToken = MutableStateFlow(initialAuthToken)
    private val mutableSecurityStrategy = MutableStateFlow(initialSecurityStrategy)
    override val hostname: StateFlow<String?> = mutableHostname
    override val securityStrategy: StateFlow<SecurityStrategy> = mutableSecurityStrategy
    override val authToken: StateFlow<AuthToken?> = mutableAuthToken

    override suspend fun setHostname(hostname: String?) {
        mutableHostname.value = hostname
    }

    override suspend fun setAuthToken(token: AuthToken?) {
        mutableAuthToken.value = token
    }

    override suspend fun setSecurityStrategy(securityStrategy: SecurityStrategy) {
        mutableSecurityStrategy.value = securityStrategy
    }
}
