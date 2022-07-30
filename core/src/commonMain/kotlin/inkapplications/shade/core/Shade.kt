package inkapplications.shade.core

import inkapplications.shade.internals.InternalsModule
import inkapplications.shade.structures.SecurityStrategy
import inkapplications.shade.lights.ShadeLightsModule
import inkapplications.shade.structures.ApplicationKey

class Shade(
    hostname: String? = null,
    applicationKey: ApplicationKey? = null,
    securityStrategy: SecurityStrategy = SecurityStrategy.PlatformTrust,
) {
    private val internalsModule = InternalsModule(
        hostname = hostname,
        applicationKey = applicationKey,
        securityStrategy = securityStrategy,
    )

    val lights = ShadeLightsModule(internalsModule).lights
}
