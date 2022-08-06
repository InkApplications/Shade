package inkapplications.shade.lights

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.builtins.serializer
import kotlin.jvm.JvmInline

/**
 * Wraps alert effects for lighting
 */
@JvmInline
value class AlertEffectType private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val Breathe = AlertEffectType("breathe")
        fun values(): Array<AlertEffectType> = arrayOf(Breathe)
        fun valueOf(key: String) = values().single { it.key == key }
    }

    internal object Serializer: DelegateSerializer<String, AlertEffectType>(String.serializer()) {
        override fun serialize(data: AlertEffectType): String = data.key
        override fun deserialize(data: String): AlertEffectType = AlertEffectType(data)
    }
}
