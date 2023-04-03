package inkapplications.shade.lights.events

import inkapplications.shade.serialization.MiredSerializer
import inkapplications.shade.structures.UndocumentedApi
import inkapplications.spondee.measure.ColorTemperature
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Information about the bulb's color temperature and capabilities.
 */
@Serializable
@UndocumentedApi
data class ColorTemperatureInfoEvent(
    /**
     * Current color temperature of the bulb,
     *
     * This value can be null if the color is not in the ct spectrum
     */
    @SerialName("mirek")
    @Serializable(with = MiredSerializer::class)
    val temperature: ColorTemperature?,

    /**
     * Indication whether the value is valid for this hardware.
     */
    @SerialName("mirek_valid")
    val valid: Boolean,
)
