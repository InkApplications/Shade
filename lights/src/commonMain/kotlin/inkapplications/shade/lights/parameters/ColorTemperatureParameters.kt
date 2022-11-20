package inkapplications.shade.lights.parameters

import inkapplications.shade.serialization.MiredSerializer
import inkapplications.spondee.measure.ColorTemperature
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Color temperature setting for a light
 */
@Serializable
data class ColorTemperatureParameters(
    /**
     * Color temperature to set the light to
     */
    @Serializable(with = MiredSerializer::class)
    @SerialName("mirek")
    val temperature: ColorTemperature? = null,
)
