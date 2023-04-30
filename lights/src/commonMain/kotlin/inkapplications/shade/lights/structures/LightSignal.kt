package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Indicates which signal is currently active for a light signal.
 */
@Serializable
@JvmInline
value class LightSignal private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val NoSignal = LightSignal("no_signal")
        val OnOff = LightSignal("on_off")

        fun values(): Array<LightSignal> = arrayOf(NoSignal, OnOff)
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
