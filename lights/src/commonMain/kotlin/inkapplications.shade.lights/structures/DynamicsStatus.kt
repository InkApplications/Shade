package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Wraps potential values for [LightDynamics] state.
 */
@JvmInline
@Serializable
value class DynamicsStatus private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val DynamicPalette = DynamicsStatus("dynamic_palette")
        val None = DynamicsStatus("none")
        fun values(): Array<DynamicsStatus> = arrayOf(None, DynamicPalette)
        fun valueOf(key: String) = values().single { it.key == key }
    }
}
