package inkapplications.shade.button

import inkapplications.shade.internals.InternalsModule

/**
 * Provides access to button control services.
 */
class ShadeButtonsModule(
    internalsModule: InternalsModule,
) {
    val buttons: ButtonControls = ShadeButtons(internalsModule.hueHttpClient)
}

