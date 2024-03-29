package inkapplications.shade.structures

import kotlinx.serialization.Serializable

/**
 * Describes the power state of a device.
 */
@Serializable
data class PowerValue(
    /**
     * Whether the device is currently powered on.
     */
    val on: Boolean,
)
