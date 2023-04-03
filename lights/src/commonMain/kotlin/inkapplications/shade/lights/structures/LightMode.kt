package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Wrap's a light mode flag/values
 */
@JvmInline
@Serializable
value class LightMode private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val Normal = LightMode("normal")
        val Streaming = LightMode("streaming")

        fun values(): Array<LightMode> = arrayOf(Normal, Streaming)
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
