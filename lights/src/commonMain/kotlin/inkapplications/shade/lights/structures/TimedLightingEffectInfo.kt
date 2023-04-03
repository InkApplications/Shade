package inkapplications.shade.lights.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Basic feature containing timed effect properties.
 */
@Serializable
data class TimedLightingEffectInfo(
    /**
     * Current lighting effect
     */
    val status: TimedLightEffect,

    /**
     * List of supported lighting effects on this device.
     */
    @SerialName("effect_values")
    val values: List<TimedLightEffect>
)
