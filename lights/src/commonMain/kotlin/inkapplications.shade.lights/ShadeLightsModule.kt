package inkapplications.shade.lights

import inkapplications.shade.internals.InternalsModule

/**
 * Provides Access to Light control services.
 */
class ShadeLightsModule(
    internalsModule: InternalsModule,
) {
    val lights: LightControls = ShadeLights(internalsModule.hueHttpClient)
}
