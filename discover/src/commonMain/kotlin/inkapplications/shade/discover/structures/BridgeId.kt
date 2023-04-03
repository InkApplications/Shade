package inkapplications.shade.discover.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Wraps a bridge ID value.
 */
@JvmInline
@Serializable
value class BridgeId(val value: String) {
    override fun toString(): String = value
}
