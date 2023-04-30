package inkapplications.shade.lights.structures

import inkapplications.shade.serialization.MiredSerializer
import inkapplications.spondee.measure.ColorTemperature
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Describes the current color temperature of a light.
 */
@Serializable
data class ColorTemperatureValue(
    /**
     * Current color temperature of the bulb,
     *
     * This value can be null if the color is not in the ct spectrum
     */
    @SerialName("mirek")
    @Serializable(with = MiredSerializer::class)
    val temperature: ColorTemperature?,
)
