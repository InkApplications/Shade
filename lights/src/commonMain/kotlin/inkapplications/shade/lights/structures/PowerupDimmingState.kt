package inkapplications.shade.lights.structures

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Dimming/Brightness state to be activated on powerup.
 */
@Serializable(with = PowerupDimmingState.Serializer::class)
abstract class PowerupDimmingState private constructor() {
    /**
     * Set a fixed dimming state on powerup.
     */
    data class StaticBrightness(
        val dimmingValue: DimmingValue,
    ): PowerupDimmingState()

    /**
     * Restore the previous dimming state on powerup.
     */
    class Previous: PowerupDimmingState()

    /**
     * Dimming state unsupported by this SDK.
     */
    internal data class Unknown(
        val mode: String,
    ): PowerupDimmingState()

    /**
     * Raw schema sent by the hue bridge for dimming
     */
    @Serializable
    internal data class PowerupDimmingStateSchema(
        val mode: String,
        @SerialName("dimming")
        val dimmingValue: DimmingValue? = null,
    )

    internal object Serializer: DelegateSerializer<PowerupDimmingStateSchema, PowerupDimmingState>(
        PowerupDimmingStateSchema.serializer(),
    ) {
        override fun serialize(data: PowerupDimmingState): PowerupDimmingStateSchema {
            return when (data) {
                is StaticBrightness -> PowerupDimmingStateSchema(
                    mode = "dimming",
                    dimmingValue = data.dimmingValue,
                )
                is Previous -> PowerupDimmingStateSchema(
                    mode = "previous",
                )
                else -> throw IllegalStateException("Cannot serialize unknown dimming schema: $data")
            }
        }

        override fun deserialize(data: PowerupDimmingStateSchema): PowerupDimmingState {
            return when (data.mode) {
                "dimming" -> StaticBrightness(
                    dimmingValue = data.dimmingValue!!,
                )
                "previous" -> Previous()
                else -> Unknown(
                    mode = data.mode,
                )
            }
        }
    }
}
