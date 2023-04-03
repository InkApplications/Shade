package inkapplications.shade.lights.parameters

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Defines the type of delta argument provided in a parameter.
 */
@JvmInline
@Serializable
value class DeltaAction private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val Up = DeltaAction("up")
        val Down = DeltaAction("down")
        val Stop = DeltaAction("stop")

        fun values(): Array<DeltaAction> = arrayOf(Up, Down, Stop)
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
