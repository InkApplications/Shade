package inkapplications.shade.lights.structures

import inkapplications.shade.serialization.DelegateSerializer
import inkapplications.shade.structures.PowerValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Power state to restore on light powerup.
 */
@Serializable(with = PowerupPowerState.Serializer::class)
abstract class PowerupPowerState private constructor(){
    /**
     * Set a static on/off state when power is restored.
     */
    data class StaticPower(
        /**
         * Power state to restore when the light turns on.
         */
        val powerValue: PowerValue,
    ): PowerupPowerState()

    /**
     * Alternate between on and off on each subsequent power toggle.
     */
    class Toggle: PowerupPowerState()

    /**
     * Return to the state it was in before powering off.
     */
    class Previous: PowerupPowerState()

    /**
     * A power state that is unsupported by this SDK.
     */
    internal data class Unknown(
        /**
         * The raw key used to describe this power state mode.
         */
        val mode: String
    ): PowerupPowerState()

    /**
     * Raw schema sent by the hue bridge for the power state.
     */
    @Serializable
    internal data class PowerupPowerStateSchema(
        val mode: String,
        @SerialName("on")
        val power: PowerValue? = null,
    )

    internal object Serializer: DelegateSerializer<PowerupPowerStateSchema, PowerupPowerState>(
        PowerupPowerStateSchema.serializer()
    ) {
        override fun serialize(data: PowerupPowerState): PowerupPowerStateSchema {
            return when (data) {
                is StaticPower -> PowerupPowerStateSchema(
                    mode = "on",
                    power = data.powerValue,
                )
                is Toggle -> PowerupPowerStateSchema(
                    mode = "toggle",
                )
                is Previous -> PowerupPowerStateSchema(
                    mode = "previous",
                )
                else -> throw IllegalStateException("Cannot serialize unknown power state schema: $data")
            }
        }

        override fun deserialize(data: PowerupPowerStateSchema): PowerupPowerState {
            return when (data.mode) {
                "on" -> StaticPower(powerValue = data.power!!)
                "toggle" -> Toggle()
                "previous" -> Previous()
                else -> Unknown(mode = data.mode)
            }
        }
    }
}

