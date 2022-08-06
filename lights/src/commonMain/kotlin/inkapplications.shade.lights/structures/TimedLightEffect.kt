package inkapplications.shade.lights.structures

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlin.jvm.JvmInline

/**
 * Timed Effect applied to a light
 */
@JvmInline
@Serializable(with = TimedLightEffect.Serializer::class)
value class TimedLightEffect private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val Sunrise = TimedLightEffect("sunrise")
        val None = TimedLightEffect("no_effect")

        fun values(): Array<TimedLightEffect> = arrayOf(Sunrise, None)
        fun valueOf(key: String) = values().single { it.key == key }
    }

    internal object Serializer: DelegateSerializer<String, TimedLightEffect>(String.serializer()) {
        override fun serialize(data: TimedLightEffect): String = data.key
        override fun deserialize(data: String): TimedLightEffect = TimedLightEffect(data)
    }
}
