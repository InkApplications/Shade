package inkapplications.shade.lightlevel

import inkapplications.shade.internals.InternalsModule

/**
 * Provides access to light level sensor services.
 *
 * These are offered by devices with light level sensing capabilities.
 */
class ShadeLightLevelModule(
    internalsModule: InternalsModule,
) {
    val lightLevels: LightLevelControls = ShadeLightLevel(internalsModule.hueHttpClient)
}
