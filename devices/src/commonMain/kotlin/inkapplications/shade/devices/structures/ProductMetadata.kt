package inkapplications.shade.devices.structures

import kotlinx.serialization.Serializable

/**
 * Configured metadata for a specific [Device].
 */
@Serializable
data class ProductMetadata(
    /**
     * Human readable name for the device.
     */
    val name: String,

    /**
     * User-configured archetype for the device or default given by the
     * manufacturer.
     */
    val archetype: ProductArchetype,
)
