package inkapplications.shade.lights.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Color temperature/brightness pair reference.
 */
@Serializable
data class ColorTemperaturePalette(
    @SerialName("color_temperature")
    val colorTemperature: ColorTemperatureValue,
    val dimming: DimmingValue,
)
