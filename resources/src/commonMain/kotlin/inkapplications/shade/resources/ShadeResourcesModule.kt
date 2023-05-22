package inkapplications.shade.resources

import inkapplications.shade.internals.InternalsModule

/**
 * Module for accessing Resource information.
 */
class ShadeResourcesModule(
    internalsModule: InternalsModule,
) {
    val resources: ResourceControls = ShadeResources(internalsModule.hueHttpClient)
}
