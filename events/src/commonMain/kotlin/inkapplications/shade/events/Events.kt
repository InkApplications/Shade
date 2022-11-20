package inkapplications.shade.events

import inkapplications.shade.structures.UndocumentedApi
import kotlinx.coroutines.flow.Flow

/**
 * Event streams available from the shade client.
 */
@UndocumentedApi
interface Events {
    /**
     * Events emitted by the hue bridge.
     */
    fun bridgeEvents(): Flow<List<Any>>
}
