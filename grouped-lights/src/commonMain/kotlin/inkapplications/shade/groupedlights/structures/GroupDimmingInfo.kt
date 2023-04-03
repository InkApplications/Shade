package inkapplications.shade.groupedlights.structures

import inkapplications.shade.serialization.WholePercentageSerializer
import inkapplications.spondee.scalar.Percentage
import kotlinx.serialization.Serializable

/**
 * Info about a light group's dimming status and capabilities.
 */
@Serializable
data class GroupDimmingInfo(
    /**
     * Current brightness value.
     */
    @Serializable(with = WholePercentageSerializer::class)
    val brightness: Percentage,
)
