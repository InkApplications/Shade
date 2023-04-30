package inkapplications.shade.lights.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains information on a single gradient point.
 */
@Serializable
data class GradientPoint(
    @SerialName("color")
    val colorValue: ColorValue,
)
