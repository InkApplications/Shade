package inkapplications.shade.scenes

import inkapplications.shade.internals.InternalsModule

/**
 * Provides Access to scene services.
 */
class ShadeScenesModule(
    internalsModule: InternalsModule,
) {
    val scenes: SceneControls = ShadeScenes(internalsModule.hueHttpClient)
}
