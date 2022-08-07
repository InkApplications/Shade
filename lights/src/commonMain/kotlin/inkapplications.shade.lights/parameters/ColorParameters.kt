package inkapplications.shade.lights.parameters

import com.github.ajalt.colormath.Color
import inkapplications.shade.lights.structures.Chromaticity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Color setting parameters for a light
 */
@Serializable
data class ColorParameters(
    /**
     * Color to set the light to
     */
    @SerialName("xy")
    @Serializable(with = Chromaticity.ColorSerializer::class)
    val color: Color? = null,
)
