package inkapplications.shade.devices

import inkapplications.shade.devices.structures.Device
import inkapplications.shade.structures.ResourceId

/**
 * Actions to get and configure Device info on the hue system.
 */
interface DeviceControls {
    /**
     * Get a list of available devices on the Hue Bridge.
     */
    suspend fun listDevices(): List<Device>

    /**
     * Get a specific device by ID.
     *
     * @param deviceId The ID of the device to fetch.
     */
    suspend fun getDevice(deviceId: ResourceId): Device
}
