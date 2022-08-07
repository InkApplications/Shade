package inkapplications.shade.core

import inkapplications.shade.discover.DiscoverModule
import inkapplications.shade.internals.InternalsModule
import inkapplications.shade.structures.SecurityStrategy
import inkapplications.shade.lights.ShadeLightsModule
import inkapplications.shade.structures.ApplicationKey
import kimchi.logger.EmptyLogger
import kimchi.logger.KimchiLogger

class Shade(
    hostname: String? = null,
    applicationKey: ApplicationKey? = null,
    securityStrategy: SecurityStrategy = SecurityStrategy.PlatformTrust,
    logger: KimchiLogger = EmptyLogger,
) {
    private val internalsModule = InternalsModule(
        hostname = hostname,
        applicationKey = applicationKey,
        securityStrategy = securityStrategy,
        logger = logger,
    )

    val discover = DiscoverModule()
    val lights = ShadeLightsModule(internalsModule).lights
}
