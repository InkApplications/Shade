package inkapplications.shade.lights.structures

import com.github.ajalt.colormath.Color
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Color information for a single gradient point.
 */
@Serializable
data class GradientColorInfo(
    /**
     * The color at this point in the gradient
     */
    @Serializable(with = Chromaticity.ColorSerializer::class)
    @SerialName("xy")
    val color: Color,
)
