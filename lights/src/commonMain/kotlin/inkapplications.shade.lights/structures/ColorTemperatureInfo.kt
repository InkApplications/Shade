package inkapplications.shade.lights.structures

import inkapplications.shade.serialization.DelegateSerializer
import inkapplications.shade.serialization.MiredSerializer
import inkapplications.spondee.measure.ColorTemperature
import inkapplications.spondee.measure.Mireds
import inkapplications.spondee.structure.value
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

/**
 * Information about the bulb's color temperature and capabilities.
 */
@Serializable
data class ColorTemperatureInfo(
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

    /**
     * Range of color temperature that this hardware supports
     */
    @SerialName("mirek_schema")
    @Serializable(with = MirekSchemaSerializer::class)
    val range: ColorTemperatureRange,
) {
    @Serializable
    internal data class MirekSchema(
        val mirek_minimum: Int,
        val mirek_maximum: Int,
    )

    internal object MirekSchemaSerializer: DelegateSerializer<MirekSchema, ColorTemperatureRange>(MirekSchema.serializer()) {
        override fun serialize(data: ColorTemperatureRange): MirekSchema {
            return MirekSchema(
                mirek_minimum = data.coolest.value(Mireds).roundToInt(),
                mirek_maximum = data.warmest.value(Mireds).roundToInt(),
            )
        }

        override fun deserialize(data: MirekSchema): ColorTemperatureRange {
            return ColorTemperatureRange(
                coolest = Mireds.of(data.mirek_minimum),
                warmest = Mireds.of(data.mirek_maximum),
            )
        }
    }
}
