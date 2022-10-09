package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Static effect applied to a light.
 */
@JvmInline
@Serializable
value class LightEffect private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val Fire = LightEffect("fire")
        val Candle = LightEffect("candle")
        val None = LightEffect("no_effect")

        fun values(): Array<LightEffect> = arrayOf(Fire, Candle, None)
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
