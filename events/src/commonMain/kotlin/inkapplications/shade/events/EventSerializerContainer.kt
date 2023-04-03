package inkapplications.shade.events

import kotlinx.serialization.DeserializationStrategy

/**
 * Stores the serializers used for deserializing event stream data.
 */
interface EventSerializerContainer {
    /**
     * Register a deserializer for a specific event type.
     *
     * @param type The key used to designate this type on the json object.
     * @param deserializer Deserializer used for events of [type]
     */
    fun setDeserializer(type: String, deserializer: DeserializationStrategy<out Any>)
}
