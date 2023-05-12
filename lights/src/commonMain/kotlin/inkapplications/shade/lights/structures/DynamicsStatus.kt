package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Wraps potential values for [LightDynamics] state.
 */
@JvmInline
@Serializable
value class DynamicsStatus(val key: String) {
    override fun toString(): String = key

    companion object {
        val DynamicPalette = DynamicsStatus("dynamic_palette")
        val None = DynamicsStatus("none")

        @Deprecated("This is an unbounded set of values. The values provided here are not exhaustive and will be removed in a future release.")
        fun values(): Array<DynamicsStatus> = arrayOf(None, DynamicPalette)

        @Deprecated(
            message = "Deprecated in favor of constructor",
            replaceWith = ReplaceWith("DynamicsStatus(key)"),
        )
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
