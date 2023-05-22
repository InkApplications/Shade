package inkapplications.shade.lights.parameters

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Defines the type of delta argument provided in a parameter.
 */
@JvmInline
@Serializable
value class DeltaAction(val key: String) {
    override fun toString(): String = key

    companion object {
        val Up = DeltaAction("up")
        val Down = DeltaAction("down")
        val Stop = DeltaAction("stop")

        @Deprecated("This is an unbounded set of values. The values provided here are not exhaustive and will be removed in a future release.")
        fun values(): Array<DeltaAction> = arrayOf(Up, Down, Stop)

        @Deprecated(
            message = "Deprecated in favor of constructor",
            replaceWith = ReplaceWith("DeltaAction(key)"),
        )
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
