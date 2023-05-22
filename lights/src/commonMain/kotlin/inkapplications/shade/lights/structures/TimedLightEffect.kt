package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Timed Effect applied to a light
 */
@JvmInline
@Serializable
value class TimedLightEffect(val key: String) {
    override fun toString(): String = key

    companion object {
        val Sunrise = TimedLightEffect("sunrise")
        val None = TimedLightEffect("no_effect")

        @Deprecated("This is an unbounded set of values. The values provided here are not exhaustive and will be removed in a future release.")
        fun values(): Array<TimedLightEffect> = arrayOf(Sunrise, None)

        @Deprecated(
            message = "Deprecated in favor of constructor",
            replaceWith = ReplaceWith("TimedLightEffect(key)"),
        )
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
