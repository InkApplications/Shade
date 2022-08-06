package inkapplications.shade.lights

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains information on a single gradient point.
 */
@Serializable
data class GradientPoint(
    /**
     * Color info for this point.
     */
    @SerialName("color")
    val colorInfo: GradientColorInfo,
)
