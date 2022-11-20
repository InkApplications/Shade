package inkapplications.shade.lights.structures

import com.github.ajalt.colormath.Color
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Information about a light's color and color capabilities.
 */
@Serializable
data class ColorInfo(
    /**
     * Current color of the light
     */
    @SerialName("xy")
    @Serializable(with = Chromaticity.ColorSerializer::class)
    val color: Color,

    /**
     * Simple Hue gamut type
     */
    @SerialName("gamut_type")
    val gamutType: GamutType,

    /**
     * Color gamut of color bulb. Some bulbs do not properly return the Gamut information. In this case this is not present.
     */
    @Serializable
    val gamut: Gamut? = null,
)
