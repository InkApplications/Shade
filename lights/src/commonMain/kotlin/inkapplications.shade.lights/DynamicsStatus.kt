package inkapplications.shade.lights

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.builtins.serializer

/**
 * Wraps potential values for [LightDynamics] state.
 */
open class DynamicsStatus private constructor(val key: String) {
    object DynamicPalette: DynamicsStatus("dynamic_palette")
    object None: DynamicsStatus("none")

    override fun equals(other: Any?): Boolean = key == (other as? DynamicsStatus)?.key
    override fun hashCode(): Int = key.hashCode()
    override fun toString(): String = key

    companion object {
        fun values(): Array<DynamicsStatus> = arrayOf(None, DynamicPalette)
        fun valueOf(key: String) = values().single { it.key == key }
    }

    internal object Serializer: DelegateSerializer<String, DynamicsStatus>(String.serializer()){
        override fun serialize(data: DynamicsStatus): String = data.key
        override fun deserialize(data: String): DynamicsStatus = DynamicsStatus(data)
    }
}
