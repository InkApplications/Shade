package inkapplications.shade.devices

import inkapplications.shade.internals.InternalsModule

/**
 *
 */
class ShadeDevicesModule(
    internalsModule: InternalsModule,
) {
    val devices: DeviceControls = ShadeDevices(internalsModule.hueHttpClient)
}
