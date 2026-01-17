package inkapplications.shade.homekit

import inkapplications.shade.internals.InternalsModule

/**
 * Provides Access to homekit control services.
 */
class ShadeHomekitModule(
    internalsModule: InternalsModule,
) {
    val homekit: HomekitControls = ShadeHomekit(internalsModule.hueHttpClient)
}
