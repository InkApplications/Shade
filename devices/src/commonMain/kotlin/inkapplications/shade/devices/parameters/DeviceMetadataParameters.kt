package inkapplications.shade.devices.parameters

import inkapplications.shade.devices.structures.ProductArchetype
import kotlinx.serialization.Serializable

/**
 * User-configured metadata configurable for the device.
 */
@Serializable
data class DeviceMetadataParameters(
    /**
     * Human readable name to assign to the device.
     */
    val name: String? = null,

    /**
     * User-assigned product type of device to assign to the device.
     */
    val archetype: ProductArchetype? = null,
)
