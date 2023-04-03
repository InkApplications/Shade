package inkapplications.shade.lights.events

import com.github.ajalt.colormath.Color
import inkapplications.shade.lights.structures.Chromaticity
import inkapplications.shade.structures.UndocumentedApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Information about a light's color and color capabilities.
 */
@Serializable
@UndocumentedApi
data class ColorInfoEvent(
    /**
     * Current color of the light
     */
    @SerialName("xy")
    @Serializable(with = Chromaticity.ColorSerializer::class)
    val color: Color,
)
