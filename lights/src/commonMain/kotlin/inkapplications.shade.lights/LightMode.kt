package inkapplications.shade.lights

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlin.jvm.JvmInline

/**
 * Wrap's a light mode flag/values
 */
@JvmInline
@Serializable(with = LightMode.Serializer::class)
value class LightMode private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val Normal = LightMode("normal")
        val Streaming = LightMode("streaming")

        fun values(): Array<LightMode> = arrayOf(Normal, Streaming)
        fun valueOf(key: String) = values().single { it.key == key }
    }

    internal object Serializer: DelegateSerializer<String, LightMode>(String.serializer()) {
        override fun serialize(data: LightMode): String = data.key
        override fun deserialize(data: String): LightMode = LightMode(data)
    }
}
