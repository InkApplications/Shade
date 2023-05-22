package inkapplications.shade.devices.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Identifier for a specific model of hardware.
 */
@JvmInline
@Serializable
value class ModelId(val value: String) {
    override fun toString(): String = value
}
