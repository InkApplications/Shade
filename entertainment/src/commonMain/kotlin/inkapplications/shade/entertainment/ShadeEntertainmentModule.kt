package inkapplications.shade.entertainment

import inkapplications.shade.internals.InternalsModule

/**
 * Provides access to entertainment control services.
 */
class ShadeEntertainmentModule(
    internalsModule: InternalsModule,
) {
    val entertainment: EntertainmentControls = ShadeEntertainment(internalsModule.hueHttpClient)
}
