package inkapplications.shade.zones

import inkapplications.shade.internals.InternalsModule

class ShadeZonesModule(
    internalsModule: InternalsModule,
) {
    val zones: ZoneControls = ShadeZones(internalsModule.hueHttpClient)
}
