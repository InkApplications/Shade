package inkapplications.shade.entertainment

import inkapplications.shade.internals.InternalsModule

/**
 * Provides access to entertainment control services.
 */
class ShadeEntertainmentModule(
    internalsModule: InternalsModule,
) {
    /**
     * Management for entertainment services.
     *
     * These are offered by devices with color lighting capabilities.
     */
    val entertainment: EntertainmentControls = ShadeEntertainment(internalsModule.hueHttpClient)

    /**
     * Controls for entertainment configurations (Hue Entertainment functionality setup).
     */
    val configurations: EntertainmentConfigurationControls = ShadeEntertainmentConfigurations(internalsModule.hueHttpClient)
}
