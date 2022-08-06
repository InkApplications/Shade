package inkapplications.shade.lights

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlin.jvm.JvmInline

/**
 * Static effect applied to a light.
 */
@JvmInline
@Serializable(with = LightEffect.Serializer::class)
value class LightEffect private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val Fire = LightEffect("fire")
        val Candle = LightEffect("candle")
        val None = LightEffect("no_effect")

        fun values(): Array<LightEffect> = arrayOf(Fire, Candle, None)
        fun valueOf(key: String) = LightEffect.values().single { it.key == key }
    }

    internal object Serializer: DelegateSerializer<String, LightEffect>(String.serializer()) {
        override fun serialize(data: LightEffect): String = data.key
        override fun deserialize(data: String): LightEffect = LightEffect(data)
    }
}
