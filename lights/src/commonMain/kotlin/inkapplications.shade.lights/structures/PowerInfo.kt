package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable

/**
 * Describes the power state of a light.
 */
@Serializable
data class PowerInfo(
    /**
     * Whether the light is currently powered on.
     */
    val on: Boolean,
)
