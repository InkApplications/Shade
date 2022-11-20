package shade.events

import inkapplications.shade.events.Events
import inkapplications.shade.internals.SseClient
import inkapplications.shade.structures.UndocumentedApi
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.DeserializationStrategy

@UndocumentedApi
internal class ShadeEvents(
    private val sseClient: SseClient,
    private val eventDeserializer: DeserializationStrategy<List<Any>>,
): Events {
    override fun bridgeEvents(): Flow<List<Any>> {
        return sseClient.openSse(
            pathSegments = arrayOf("eventstream", "clip", "v2"),
            dataDeserializer = eventDeserializer,
        )
    }
}
