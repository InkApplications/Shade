package inkapplications.shade.devices

import inkapplications.shade.devices.parameters.UpdateDeviceParameters
import inkapplications.shade.devices.structures.Device
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference

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

    /**
     * Update a device's information.
     *
     * @param deviceId The ID of the device to be updated.
     * @param parameters The information to update on the device.
     * @return A reference to the updated resource.
     */
    suspend fun updateDevice(deviceId: ResourceId, parameters: UpdateDeviceParameters): ResourceReference

    /**
     * Trigger a visual identification sequence on a device.
     *
     * @param deviceId The ID of the device to start identifying
     * @return A reference to the resource.
     */
    suspend fun identifyDevice(deviceId: ResourceId): ResourceReference

    /**
     * Delete an existing device from the hue bridge.
     *
     * @param deviceId The ID of the device to be deleted.
     * @return A reference to the resource that was deleted.
     */
    suspend fun deleteDevice(deviceId: ResourceId): ResourceReference
}
