package inkapplications.shade.devices

import inkapplications.shade.devices.structures.Device
import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.deleteData
import inkapplications.shade.internals.getData
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference

/**
 * Implements Device Controls using the hue client.
 */
internal class ShadeDevices(
    private val hueHttpClient: HueHttpClient,
): DeviceControls {
    override suspend fun listDevices(): List<Device> {
        return hueHttpClient.getData("resource", "device")
    }

    override suspend fun getDevice(deviceId: ResourceId): Device {
        return hueHttpClient.getData<List<Device>>("resource", "device", deviceId.value).single()
    }

    override suspend fun deleteDevice(deviceId: ResourceId): ResourceReference {
        return hueHttpClient.deleteData("resource", "device", deviceId.value)
    }
}
