package inkapplications.shade.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Unique identifier representing a specific resource instance.
 */
@JvmInline
@Serializable
value class ResourceId(val value: String) {
    override fun toString(): String = value
}
