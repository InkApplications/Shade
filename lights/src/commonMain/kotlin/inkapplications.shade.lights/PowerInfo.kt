package inkapplications.shade.lights

import kotlinx.serialization.Serializable

/**
 * Wraps a boolean for no discernible reason.
 */
@Serializable
data class PowerInfo(
    val on: Boolean,
)
