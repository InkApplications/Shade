package inkapplications.shade.structures

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlin.jvm.JvmInline

/**
 * Unique identifier representing a specific resource instance.
 */
@JvmInline
@Serializable(with = ResourceId.Serializer::class)
value class ResourceId(val value: String) {
    override fun toString(): String = value
    internal object Serializer: DelegateSerializer<String, ResourceId>(String.serializer()) {
        override fun serialize(data: ResourceId): String = data.value
        override fun deserialize(data: String): ResourceId = ResourceId(data)
    }
}
