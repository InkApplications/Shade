package inkapplications.shade.internals

import inkapplications.shade.structures.AuthToken
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

    override suspend fun setAuthToken(key: AuthToken?) {
        mutableAuthToken.value = key
    }

    override suspend fun setSecurityStrategy(securityStrategy: SecurityStrategy) {
        mutableSecurityStrategy.value = securityStrategy
    }
}
