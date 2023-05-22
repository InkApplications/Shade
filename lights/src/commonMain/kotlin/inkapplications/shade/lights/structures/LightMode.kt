package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Wrap's a light mode flag/values
 */
@JvmInline
@Serializable
value class LightMode(val key: String) {
    override fun toString(): String = key

    companion object {
        val Normal = LightMode("normal")
        val Streaming = LightMode("streaming")

        @Deprecated("This is an unbounded set of values. The values provided here are not exhaustive and will be removed in a future release.")
        fun values(): Array<LightMode> = arrayOf(Normal, Streaming)

        @Deprecated(
            message = "Deprecated in favor of constructor",
            replaceWith = ReplaceWith("LightMode(key)"),
        )
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
