package inkapplications.shade.scenes.structures

import inkapplications.shade.lights.structures.*
import inkapplications.shade.serialization.DelegateSerializer
import inkapplications.shade.serialization.MillisecondDurationSerializer
import inkapplications.shade.structures.PowerValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Duration

/**
 * Action to be executed on the lights in the scene.
 */
@Serializable
data class SceneAction(
    /**
     * On/Off state of the lights in the scene.
     */
    @SerialName("on")
    val powerValue: PowerValue? = null,

    /**
     * Dimming values of the lights in the scene, if applicable.
     */
    @SerialName("dimming")
    val dimmingValue: DimmingValue? = null,

    /**
     * Color values of the lights in the scene, if applicable.
     */
    @SerialName("color")
    val colorValue: ColorValue? = null,

    /**
     * Color temperature values of the lights in the scene, if applicable.
     */
    @SerialName("color_temperature")
    val colorTemperatureValue: ColorTemperatureValue? = null,

    /**
     * Gradient values of the lights in the scene, if applicable.
     */
    val gradient: GradientValue? = null,

    /**
     * Effect values applied to lights in the scene.
     */
    @Serializable(with = EffectsSerializer::class)
    val effect: LightEffect? = null,

    /**
     * Duration of a light transition or timed effects.
     */
    @Serializable(with = DynamicsSerializer::class)
    val dynamicsDuration: Duration? = null,
) {
    /**
     * Wrapping class for light effects, abstracted out of the implementation.
     */
    @Serializable
    private data class Effects(
        val effect: LightEffect,
    )
    private object EffectsSerializer: DelegateSerializer<Effects, LightEffect>(
        delegate = Effects.serializer(),
    ) {
        override fun serialize(data: LightEffect): Effects {
            return Effects(effect = data)
        }

        override fun deserialize(data: Effects): LightEffect {
            return data.effect
        }
    }

    /**
     * Wrapping class for dynamics duration, abstracted out of the implementation.
     */
    @Serializable
    private data class Dynamics(
        @Serializable(with = MillisecondDurationSerializer::class)
        val duration: Duration,
    )

    private object DynamicsSerializer: DelegateSerializer<Dynamics, Duration>(
        delegate = Dynamics.serializer(),
    ) {
        override fun serialize(data: Duration): Dynamics {
            return Dynamics(duration = data)
        }

        override fun deserialize(data: Dynamics): Duration {
            return data.duration
        }
    }
}
