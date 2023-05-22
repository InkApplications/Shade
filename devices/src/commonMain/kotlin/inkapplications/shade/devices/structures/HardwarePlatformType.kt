package inkapplications.shade.devices.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Hardware type as identified by Manufacturer.
 */
@JvmInline
@Serializable
value class HardwarePlatformType(val value: String) {
    override fun toString(): String = value
}
