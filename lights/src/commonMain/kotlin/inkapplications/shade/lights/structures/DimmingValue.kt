package inkapplications.shade.lights.structures

import inkapplications.shade.serialization.WholePercentageSerializer
import inkapplications.spondee.scalar.Percentage
import kotlinx.serialization.Serializable

/**
 * Info about a light's dimming status.
 */
@Serializable
data class DimmingValue(
    /**
     * Current brightness value.
     */
    @Serializable(with = WholePercentageSerializer::class)
    val brightness: Percentage,
)
