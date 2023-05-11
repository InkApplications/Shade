package inkapplications.shade.devices

import inkapplications.shade.devices.structures.Device

/**
 * Actions to get and configure Device info on the hue system.
 */
interface DeviceControls {
    /**
     * Get a list of available devices on the Hue Bridge.
     */
    suspend fun listDevices(): List<Device>
}
