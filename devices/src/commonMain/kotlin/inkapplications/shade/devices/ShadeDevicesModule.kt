package inkapplications.shade.devices

import inkapplications.shade.internals.InternalsModule

/**
 * Module for accessing Device information.
 */
class ShadeDevicesModule(
    internalsModule: InternalsModule,
) {
    val devices: DeviceControls = ShadeDevices(internalsModule.hueHttpClient)
}
