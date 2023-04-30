package inkapplications.shade.structures

import kotlinx.serialization.Serializable

/**
 * Describes the power state of a device and associated capabilities.
 */
@Serializable
data class PowerInfo(
    /**
     * Whether the device is currently powered on.
     */
    val on: Boolean,
)
