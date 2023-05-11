package inkapplications.shade.devices.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Manufacturer specified information about a device.
 */
@Serializable
data class ProductData(
    /**
     * Unique Identification of the device model.
     */
    @SerialName("model_id")
    val modelId: ModelId,

    /**
     * Name of the device's manufacturer.
     */
    @SerialName("manufacturer_name")
    val manufacturerName: String,

    /**
     * Name of the product.
     */
    @SerialName("product_name")
    val productName: String,

    /**
     * General archetype of the device, as specified by the manufacturer.
     */
    @SerialName("product_archetype")
    val productArchetype: ProductArchetype,

    /**
     * Whether the device is hue certified.
     */
    val certified: Boolean,

    /**
     * Version of the software running on the device.
     */
    @SerialName("software_version")
    val softwareVersion: VersionString,

    /**
     * Hardware type; identified by Manufacturer code and ImageType.
     */
    @SerialName("hardware_platform_type")
    val hardwarePlatformType: HardwarePlatformType? = null,
)
