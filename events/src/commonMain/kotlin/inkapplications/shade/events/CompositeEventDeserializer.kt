package inkapplications.shade.events

import inkapplications.shade.structures.UndocumentedApi
import inkapplications.shade.structures.UnknownEvent
import kimchi.logger.KimchiLogger
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.*

/**
 * Deserializes events by delegating to a serializer registered by type.
 */
@OptIn(UndocumentedApi::class)
internal class CompositeEventDeserializer(
    private val json: Json,
    private val logger: KimchiLogger,
): DeserializationStrategy<List<Any>>, EventSerializerContainer {
    private val serializers = mutableMapOf<String, DeserializationStrategy<out Any>>()
    private val backingSerializer = JsonElement.serializer()
    override val descriptor: SerialDescriptor get() = backingSerializer.descriptor

    override fun deserialize(decoder: Decoder): List<Any> {
        return backingSerializer.deserialize(decoder)
            .jsonArray
            .flatMap { it.jsonObject["data"]?.jsonArray?.toList().orEmpty() }
            .map { data ->
                data.jsonObject["type"]
                    ?.jsonPrimitive
                    ?.contentOrNull
                    ?.let { serializers[it] }
                    ?.let {
                        runCatching { json.decodeFromJsonElement(it, data) }
                            .onFailure { logger.error("Event Deserialization Failed", it) }
                            .getOrNull()
                    }
                    ?: UnknownEvent(
                        type = data.jsonObject["type"]?.jsonPrimitive?.contentOrNull ?: throw IllegalArgumentException("Data without type"),
                        json = data.toString(),
                    )
            }
    }

    override fun setDeserializer(type: String, deserializer: DeserializationStrategy<out Any>) {
        serializers[type] = deserializer
    }
}
