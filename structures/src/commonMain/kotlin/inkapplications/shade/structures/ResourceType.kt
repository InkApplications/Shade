package inkapplications.shade.structures

import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlin.jvm.JvmInline

/**
 * Discriminator key used within the hue api to determine object types
 */
@Serializable(with = ResourceType.Serializer::class)
@JvmInline
value class ResourceType private constructor(val key: String) {
    override fun toString(): String = key

    companion object {
        val Light = ResourceType("light")
        fun values(): Array<ResourceType> = arrayOf(Light)
        fun valueOf(key: String) = values().single { it.key == key }
    }

    internal object Serializer: DelegateSerializer<String, ResourceType>(String.serializer()){
        override fun serialize(data: ResourceType): String = data.key
        override fun deserialize(data: String): ResourceType = ResourceType(data)
    }
}
