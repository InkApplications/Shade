package inkapplications.shade.core

import inkapplications.shade.auth.AuthModule
import inkapplications.shade.discover.DiscoverModule
import inkapplications.shade.internals.InternalsModule
import inkapplications.shade.structures.SecurityStrategy
import inkapplications.shade.lights.ShadeLightsModule
import inkapplications.shade.structures.AuthToken
import kimchi.logger.EmptyLogger
import kimchi.logger.KimchiLogger

class Shade(
    hostname: String? = null,
    authToken: AuthToken? = null,
    securityStrategy: SecurityStrategy = SecurityStrategy.PlatformTrust,
    logger: KimchiLogger = EmptyLogger,
) {
    private val internalsModule = InternalsModule(
        hostname = hostname,
        authToken = authToken,
        securityStrategy = securityStrategy,
        logger = logger,
    )

    val auth = AuthModule(
        internalsModule = internalsModule,
        logger = logger,
    )
    val discover = DiscoverModule()
    val lights = ShadeLightsModule(internalsModule).lights
}
