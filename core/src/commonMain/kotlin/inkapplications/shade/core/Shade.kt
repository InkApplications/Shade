package inkapplications.shade.core

import inkapplications.shade.auth.AuthModule
import inkapplications.shade.devices.ShadeDevicesModule
import inkapplications.shade.discover.DiscoverModule
import inkapplications.shade.events.EventsModule
import inkapplications.shade.groupedlights.ShadeGroupedLightsModule
import inkapplications.shade.homekit.ShadeHomekitModule
import inkapplications.shade.internals.InternalsModule
import inkapplications.shade.lightlevel.ShadeLightLevelModule
import inkapplications.shade.lights.ShadeLightsModule
import inkapplications.shade.resources.ShadeResourcesModule
import inkapplications.shade.rooms.ShadeRoomsModule
import inkapplications.shade.scenes.ShadeScenesModule
import inkapplications.shade.structures.AuthToken
import inkapplications.shade.structures.HueConfigurationContainer
import inkapplications.shade.structures.InMemoryConfigurationContainer
import inkapplications.shade.structures.SecurityStrategy
import inkapplications.shade.zones.ShadeZonesModule
import kimchi.logger.EmptyLogger
import kimchi.logger.KimchiLogger

class Shade(
    val configuration: HueConfigurationContainer,
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
        configuration = InMemoryConfigurationContainer(
            initialHostname = hostname,
            initialAuthToken = authToken,
            initialSecurityStrategy = securityStrategy,
        ),
        logger = logger,
    )

    private val internalsModule = InternalsModule(
        configurationContainer = configuration,
        logger = logger,
    )
    internal val eventsModule = EventsModule(internalsModule, logger)

    val onlineDiscovery = DiscoverModule().onlineDiscovery
    val auth = AuthModule(
        internalsModule = internalsModule,
        logger = logger,
    ).bridgeAuth
    val devices = ShadeDevicesModule(internalsModule).devices
    val lights = ShadeLightsModule(internalsModule, eventsModule).lights
    val lightLevels = ShadeLightLevelModule(internalsModule).lightLevels
    val rooms = ShadeRoomsModule(internalsModule).rooms
    val zones = ShadeZonesModule(internalsModule).zones
    val groupedLights = ShadeGroupedLightsModule(internalsModule, eventsModule).groupedLights
    val resources = ShadeResourcesModule(internalsModule).resources
    val scenes = ShadeScenesModule(internalsModule).scenes
    val homekit = ShadeHomekitModule(internalsModule).homekit
}
