package inkapplications.shade.button.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Events that can be sent by a button control.
 */
@JvmInline
@Serializable
value class ButtonEvent(val key: String) {
    override fun toString(): String = key

    companion object {
        /**
         * Initial press of the button.
         */
        val InitialPress = ButtonEvent("initial_press")

        /**
         * Repeated event while holding the button.
         */
        val Repeat = ButtonEvent("repeat")

        /**
         * Released after a short press.
         */
        val ShortRelease = ButtonEvent("short_release")

        /**
         * Released after a long press.
         */
        val LongRelease = ButtonEvent("long_release")

        /**
         * Released after a double short press.
         */
        val DoubleShortRelease = ButtonEvent("double_short_release")

        /**
         * Long press detected (while still holding).
         */
        val LongPress = ButtonEvent("long_press")
    }
}
