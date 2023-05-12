package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Static effect applied to a light.
 */
@JvmInline
@Serializable
value class LightEffect(val key: String) {
    override fun toString(): String = key

    companion object {
        val Fire = LightEffect("fire")
        val Candle = LightEffect("candle")
        val None = LightEffect("no_effect")

        @Deprecated("This is an unbounded set of values. The values provided here are not exhaustive and will be removed in a future release.")
        fun values(): Array<LightEffect> = arrayOf(Fire, Candle, None)

        @Deprecated(
            message = "Deprecated in favor of constructor",
            replaceWith = ReplaceWith("LightEffect(key)"),
        )
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
