package inkapplications.shade.groupedlights

import inkapplications.shade.internals.InternalsModule

/**
 * Provides Access to grouped light services.
 */
class ShadeGroupedLightsModule(
    internalsModule: InternalsModule,
) {
    val groupedLights: GroupedLightControls = ShadeGroupedLights(internalsModule.hueHttpClient)
}
