package inkapplications.shade.lights.structures

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Properties to configure powerup behaviour of a lightsource.
 */
@Serializable(with = LightPowerup.Serializer::class)
abstract class LightPowerup private constructor() {
    /**
     * Indicates if the shown values have been configured in the lightsource.
     */
    abstract val configured: Boolean

    /**
     * Power state to activate after powerup.
     */
    abstract val powerState: PowerupPowerState

    /**
     * Restore the default state of the light on powerup.
     */
    data class Safety(
        override val configured: Boolean,
        override val powerState: PowerupPowerState,
    ): LightPowerup()

    /**
     * Restore the last known state of the light, maintaining on/off value.
     */
    data class Powerfail(
        override val configured: Boolean,
        override val powerState: PowerupPowerState,
    ): LightPowerup()

    /**
     * Restore the last known state of the light when it was on.
     */
    data class LastOnState(
        override val configured: Boolean,
        override val powerState: PowerupPowerState,
    ): LightPowerup()

    /**
     * Custom Color/Temperature setting to restore on power up.
     */
    data class Custom(
        override val configured: Boolean,
        override val powerState: PowerupPowerState,
        /**
         * Dimming state to activate after powerup
         */
        val dimmingState: PowerupDimmingState? = null,
        /**
         * Color state to activate after powerup
         */
        val colorState: PowerupColorState? = null,
    ): LightPowerup()

    /**
     * Powerup preset state that is not supported by this SDK.
     */
    internal data class Unknown(
        override val configured: Boolean,
        override val powerState: PowerupPowerState,
        val preset: String,
    ): LightPowerup()

    /**
     * Raw schema for the powerup data field.
     */
    @Serializable
    internal data class LightPowerupSchema(
        val preset: String,
        val configured: Boolean,
        @SerialName("on")
        val powerState: PowerupPowerState,
        @SerialName("dimming")
        val dimmingState: PowerupDimmingState? = null,
        @SerialName("color")
        val colorState: PowerupColorState? = null,
    )

    internal object Serializer: DelegateSerializer<LightPowerupSchema, LightPowerup>(LightPowerupSchema.serializer()) {
        override fun serialize(data: LightPowerup): LightPowerupSchema {
            return when (data) {
                is Safety -> LightPowerupSchema(
                    preset = "safety",
                    configured = data.configured,
                    powerState = data.powerState,
                )
                is Powerfail -> LightPowerupSchema(
                    preset = "powerfail",
                    configured = data.configured,
                    powerState = data.powerState,
                )
                is LastOnState -> LightPowerupSchema(
                    preset = "last_on_state",
                    configured = data.configured,
                    powerState = data.powerState,
                )
                is Custom -> LightPowerupSchema(
                    preset = "custom",
                    configured = data.configured,
                    powerState = data.powerState,
                    dimmingState = data.dimmingState,
                    colorState = data.colorState,
                )
                else -> throw IllegalStateException("Cannot serialize unknown powerup preset type: $data")
            }
        }

        override fun deserialize(data: LightPowerupSchema): LightPowerup {
            return when (data.preset) {
                "safety" -> Safety(
                    configured = data.configured,
                    powerState = data.powerState,
                )
                "powerfail" -> Powerfail(
                    configured = data.configured,
                    powerState = data.powerState,
                )
                "last_on_state" -> LastOnState(
                    configured = data.configured,
                    powerState = data.powerState,
                )
                "custom" -> Custom(
                    configured = data.configured,
                    powerState = data.powerState,
                    dimmingState = data.dimmingState,
                    colorState = data.colorState,
                )
                else -> Unknown(
                    configured = data.configured,
                    powerState = data.powerState,
                    preset = data.preset,
                )
            }
        }
    }
}
