package inkapplications.shade.internals

import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.DeserializationStrategy

/**
 * Internal client used for listening to event streams from the Hue bridge.
 */
interface SseClient {
    /**
     * Open a server-side-event stream.
     *
     * @param pathSegments The URL path to the event stream to listen to.
     * @param dataDeserializer deserializer to use for all events emitted.
     */
    fun <DATA> openSse(
        pathSegments: Array<out String>,
        dataDeserializer: DeserializationStrategy<DATA>,
    ): Flow<DATA>
}

