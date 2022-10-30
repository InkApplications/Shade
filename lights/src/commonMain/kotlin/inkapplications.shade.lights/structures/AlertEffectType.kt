package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Wraps alert effects for lighting
 */
@JvmInline
@Serializable
value class AlertEffectType private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val Breathe = AlertEffectType("breathe")
        fun values(): Array<AlertEffectType> = arrayOf(Breathe)
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
