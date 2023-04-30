package inkapplications.shade.lights.structures

import com.github.ajalt.colormath.Color
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Color information for a single gradient point.
 */
@Serializable
@Deprecated("Use ColorValue")
data class GradientColorInfo
@Deprecated(
    message = "Use ColorValue",
    replaceWith = ReplaceWith("ColorValue(color)", "inkapplications.shade.lights.structures.ColorValue")
)
constructor (
    /**
     * The color at this point in the gradient
     */
    @Serializable(with = Chromaticity.ColorSerializer::class)
    @SerialName("xy")
    val color: Color,
)
