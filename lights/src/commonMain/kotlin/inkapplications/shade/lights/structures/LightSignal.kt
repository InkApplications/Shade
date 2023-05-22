package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Indicates which signal is currently active for a light signal.
 */
@Serializable
@JvmInline
value class LightSignal(val key: String) {
    override fun toString(): String = key

    companion object {
        val NoSignal = LightSignal("no_signal")
        val OnOff = LightSignal("on_off")

        @Deprecated("This is an unbounded set of values. The values provided here are not exhaustive and will be removed in a future release.")
        fun values(): Array<LightSignal> = arrayOf(NoSignal, OnOff)

        @Deprecated(
            message = "Deprecated in favor of constructor",
            replaceWith = ReplaceWith("LightSignal(key)"),
        )
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
