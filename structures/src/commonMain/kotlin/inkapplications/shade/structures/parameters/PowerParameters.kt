package inkapplications.shade.structures.parameters

import kotlinx.serialization.Serializable

/**
 * Power settings for a device
 */
@Serializable
data class PowerParameters(
    /**
     * Simple on/off state for the device
     */
    val on: Boolean? = null,
)
