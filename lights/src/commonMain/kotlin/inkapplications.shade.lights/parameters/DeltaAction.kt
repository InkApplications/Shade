package inkapplications.shade.lights.parameters

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlin.jvm.JvmInline

/**
 * Defines the type of delta argument provided in a parameter.
 */
@JvmInline
@Serializable(with = DeltaAction.Serializer::class)
value class DeltaAction private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val Up = DeltaAction("up")
        val Down = DeltaAction("down")
        val Stop = DeltaAction("stop")

        fun values(): Array<DeltaAction> = arrayOf(Up, Down, Stop)
        fun valueOf(key: String) = values().single { it.key == key }
    }

    internal object Serializer: DelegateSerializer<String, DeltaAction>(String.serializer()) {
        override fun serialize(data: DeltaAction): String = data.key
        override fun deserialize(data: String): DeltaAction = DeltaAction(data)
    }
}
