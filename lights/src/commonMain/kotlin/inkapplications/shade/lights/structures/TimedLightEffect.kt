package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Timed Effect applied to a light
 */
@JvmInline
@Serializable
value class TimedLightEffect private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val Sunrise = TimedLightEffect("sunrise")
        val None = TimedLightEffect("no_effect")

        fun values(): Array<TimedLightEffect> = arrayOf(Sunrise, None)
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
