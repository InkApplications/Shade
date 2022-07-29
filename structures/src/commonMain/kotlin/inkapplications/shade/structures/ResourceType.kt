package inkapplications.shade.structures

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

/**
 * Discriminator key used within the hue api to determine object types
 */
@Serializable(with = ResourceType.Serializer::class)
open class ResourceType private constructor(val key: String) {
    object Light: ResourceType("light")

    override fun equals(other: Any?): Boolean = key == (other as? ResourceType)?.key
    override fun hashCode(): Int = key.hashCode()
    override fun toString(): String = key

    companion object {
        fun values(): Array<ResourceType> = arrayOf(Light)
        fun valueOf(key: String) = values().single { it.key == key }
    }

    internal object Serializer: DelegateSerializer<String, ResourceType>(String.serializer()){
        override fun serialize(data: ResourceType): String = data.key
        override fun deserialize(data: String): ResourceType = ResourceType(data)
    }
}
