package inkapplications.shade.lights.structures

import com.github.ajalt.colormath.Color
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Information about a light's color.
 */
@Serializable
data class ColorValue(
    /**
     * Current color of the light
     */
    @SerialName("xy")
    @Serializable(with = Chromaticity.ColorSerializer::class)
    val color: Color,
)
