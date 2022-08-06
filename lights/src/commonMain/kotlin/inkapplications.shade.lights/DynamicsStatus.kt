package inkapplications.shade.lights

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlin.jvm.JvmInline

/**
 * Wraps potential values for [LightDynamics] state.
 */
@JvmInline
@Serializable(with = DynamicsStatus.Serializer::class)
value class DynamicsStatus private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val DynamicPalette = DynamicsStatus("dynamic_palette")
        val None = DynamicsStatus("none")
        fun values(): Array<DynamicsStatus> = arrayOf(None, DynamicPalette)
        fun valueOf(key: String) = values().single { it.key == key }
    }

    internal object Serializer: DelegateSerializer<String, DynamicsStatus>(String.serializer()){
        override fun serialize(data: DynamicsStatus): String = data.key
        override fun deserialize(data: String): DynamicsStatus = DynamicsStatus(data)
    }
}
