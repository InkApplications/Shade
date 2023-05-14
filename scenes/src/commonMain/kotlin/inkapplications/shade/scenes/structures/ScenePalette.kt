package inkapplications.shade.scenes.structures

import inkapplications.shade.lights.structures.ColorPalette
import inkapplications.shade.lights.structures.ColorTemperaturePalette
import inkapplications.shade.lights.structures.DimmingValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Group of colors that describe the palette of colors to be used when playing dynamics
 */
@Serializable
data class ScenePalette(
    /**
     * List of colors to be used when playing dynamics.
     */
    val color: List<ColorPalette>? = null,

    /**
     * List of colors to be used when playing dynamics.
     */
    val dimming: List<DimmingValue>? = null,

    /**
     * List of colors to be used when playing dynamics.
     */
    @SerialName("color_temperature")
    val colorTemperature: List<ColorTemperaturePalette>? = null,
)
