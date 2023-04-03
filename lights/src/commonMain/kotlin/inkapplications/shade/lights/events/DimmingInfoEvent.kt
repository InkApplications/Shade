package inkapplications.shade.lights.events

import inkapplications.shade.serialization.WholePercentageSerializer
import inkapplications.shade.structures.UndocumentedApi
import inkapplications.spondee.scalar.Percentage
import kotlinx.serialization.Serializable

/**
 * Info about a light's dimming status and capabilities.
 */
@Serializable
@UndocumentedApi
data class DimmingInfoEvent(
    /**
     * Current brightness value.
     */
    @Serializable(with = WholePercentageSerializer::class)
    val brightness: Percentage,
)
