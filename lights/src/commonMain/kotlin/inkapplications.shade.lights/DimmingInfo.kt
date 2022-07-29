package inkapplications.shade.lights

import inkapplications.shade.serialization.PercentageSerializer
import inkapplications.spondee.scalar.Percentage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Info about a light's dimming status and capabilities.
 */
@Serializable
data class DimmingInfo(
    /**
     * Current brightness value.
     */
    @Serializable(with = PercentageSerializer::class)
    val brightness: Percentage,

    /**
     * Percentage of the maximum lumen the device outputs on minimum brightness.
     */
    @Serializable(with = PercentageSerializer::class)
    @SerialName("min_dim_level")
    val minimum: Percentage? = null,
)
