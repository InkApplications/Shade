package inkapplications.shade.events

import inkapplications.shade.internals.InternalsModule
import kimchi.logger.KimchiLogger

class EventsModule(
    internal val internalsModule: InternalsModule,
    private val logger: KimchiLogger,
) {
    internal val eventDeserializer = CompositeEventDeserializer(internalsModule.json, logger)
    /**
     * Provides access to the container that configures SSE serialization
     * for the event stream.
     */
    val eventSerializerContainer: EventSerializerContainer = eventDeserializer
}
