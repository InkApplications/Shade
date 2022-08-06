package inkapplications.shade.lights

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Basic feature containing effect properties.
 */
@Serializable
data class LightingEffectInfo(
    /**
     * Current lighting effect
     */
    val status: LightEffect,

    /**
     * List of supported lighting effects on this device.
     */
    @SerialName("effect_values")
    val values: List<LightEffect>,
)
