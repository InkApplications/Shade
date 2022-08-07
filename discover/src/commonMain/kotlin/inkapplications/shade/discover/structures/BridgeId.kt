package inkapplications.shade.discover.structures

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlin.jvm.JvmInline

/**
 * Wraps a bridge ID value.
 */
@JvmInline
@Serializable(with = BridgeId.Serializer::class)
value class BridgeId(val value: String) {
    override fun toString(): String = value
    internal object Serializer: DelegateSerializer<String, BridgeId>(String.serializer()) {
        override fun serialize(data: BridgeId): String = data.value
        override fun deserialize(data: String): BridgeId = BridgeId(data)
    }
}
