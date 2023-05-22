package inkapplications.shade.devices.structures

import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Attributes for a device configured on the hue bridge.
 */
@Serializable
data class Device(
    /**
     * Unique ID for the device.
     */
    val id: ResourceId,

    /**
     * Clip v1 resource identifier.
     */
    @Deprecated("V1 Resource. Left for migration purposes only, may be removed at any point by API or SDK.")
    @SerialName("id_v1")
    val v1Id: String? = null,

    /**
     * Information about the hardware itself.
     */
    @SerialName("product_data")
    val productData: ProductData,

    /**
     * Configured metadata for this device.
     */
    val metadata: ProductMetadata,

    /**
     * References all services providing control and state of the device.
     */
    val services: List<ResourceReference>,
)
