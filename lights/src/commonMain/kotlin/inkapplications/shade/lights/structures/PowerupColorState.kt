package inkapplications.shade.lights.structures

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Color state properties for powerup.
 */
@Serializable(with = PowerupColorState.Serializer::class)
abstract class PowerupColorState private constructor() {
    /**
     * Set a static color at powerup.
     */
    data class Color(
        /**
         * The color that is set to the light at powerup.
         */
        val colorValue: ColorValue,

        /**
         * Color Temperature to set on powerup.
         *
         * This property will be null when the light color is not in the
         * ct spectrum.
         */
        val temperatureValue: ColorTemperatureValue? = null,
    ): PowerupColorState()

    /**
     * Set a static color temperature at powerup.
     */
    data class ColorTemperature(
        /**
         * Color Temperature to set on powerup.
         */
        val temperatureValue: ColorTemperatureValue,
    ): PowerupColorState()

    /**
     * Restore the previous color at powewup.
     */
    class Previous: PowerupColorState()

    /**
     * Powerup state that is unsupported by this SDK.
     */
    internal data class Unknown(
        val mode: String,
    ): PowerupColorState()

    /**
     * Raw color state schema sent by the api
     */
    @Serializable
    internal data class PowerupColorStateSchema(
        val mode: String,
        @SerialName("color_temperature")
        val temperatureValue: ColorTemperatureValue? = null,
        @SerialName("color")
        val colorValue: ColorValue? = null
    )

    internal object Serializer: DelegateSerializer<PowerupColorStateSchema, PowerupColorState>(
        PowerupColorStateSchema.serializer(),
    ) {
        override fun serialize(data: PowerupColorState): PowerupColorStateSchema {
            return when (data) {
                is Color -> PowerupColorStateSchema(
                    mode = "color",
                    temperatureValue = data.temperatureValue,
                    colorValue = data.colorValue,
                )
                is ColorTemperature -> PowerupColorStateSchema(
                    mode = "color_temperature",
                    temperatureValue = data.temperatureValue,
                )
                is Previous -> PowerupColorStateSchema(
                    mode = "previous",
                )
                else -> throw IllegalArgumentException("Cannot serialize unknown color state schema: $data")
            }
        }

        override fun deserialize(data: PowerupColorStateSchema): PowerupColorState {
            return when (data.mode) {
                "color" -> Color(
                    colorValue = data.colorValue!!,
                    temperatureValue = data.temperatureValue,
                )
                "color_temperature" -> ColorTemperature(
                    temperatureValue = data.temperatureValue!!,
                )
                "previous" -> Previous()
                else -> Unknown(mode = data.mode)
            }
        }
    }
}
