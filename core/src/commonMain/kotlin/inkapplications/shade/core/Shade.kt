package inkapplications.shade.core

import inkapplications.shade.auth.AuthModule
import inkapplications.shade.discover.DiscoverModule
import inkapplications.shade.internals.InternalsModule
import inkapplications.shade.lights.ShadeLightsModule
import inkapplications.shade.rooms.ShadeRoomsModule
import inkapplications.shade.structures.AuthToken
import inkapplications.shade.structures.HueConfigurationContainer
import inkapplications.shade.structures.InMemoryConfigurationContainer
import inkapplications.shade.structures.SecurityStrategy
import kimchi.logger.EmptyLogger
import kimchi.logger.KimchiLogger

class Shade(
    configurationContainer: HueConfigurationContainer,
    logger: KimchiLogger = EmptyLogger,
) {
    /**
     * Empty Constructor with reasonable defaults.
     */
    constructor(
        hostname: String? = null,
        authToken: AuthToken? = null,
        securityStrategy: SecurityStrategy = SecurityStrategy.PlatformTrust,
        logger: KimchiLogger = EmptyLogger,
    ): this(
        configurationContainer = InMemoryConfigurationContainer(
            initialHostname = hostname,
            initialAuthToken = authToken,
            initialSecurityStrategy = securityStrategy,
        ),
        logger = logger,
    )

    private val internalsModule = InternalsModule(
        configurationContainer = configurationContainer,
        logger = logger,
    )

    val auth = AuthModule(
        internalsModule = internalsModule,
        logger = logger,
    )
    val discover = DiscoverModule()
    val lights = ShadeLightsModule(internalsModule).lights
    val rooms = ShadeRoomsModule(internalsModule).rooms
}
