package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Wraps alert effects for lighting
 */
@JvmInline
@Serializable
value class AlertEffectType(val key: String) {
    override fun toString(): String = key

    companion object {
        val Breathe = AlertEffectType("breathe")

        @Deprecated("This is an unbounded set of values. The values provided here are not exhaustive and will be removed in a future release.")
        fun values(): Array<AlertEffectType> = arrayOf(Breathe)

        @Deprecated(
            message = "Deprecated in favor of constructor",
            replaceWith = ReplaceWith("AlertEffectType(key)"),
        )
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
