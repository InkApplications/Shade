package inkapplications.shade.lights.parameters

import kotlinx.serialization.Serializable

/**
 * Power settings for a light
 */
@Serializable
data class PowerParameters(
    /**
     * Simple on/off state for the light
     */
    val on: Boolean? = null,
)
