package shade.events

import inkapplications.shade.events.Events
import inkapplications.shade.events.EventsModule
import inkapplications.shade.structures.UndocumentedApi

@UndocumentedApi
val EventsModule.events: Events
    get() = ShadeEvents(
    sseClient = internalsModule.platformModule.sseClient,
    eventDeserializer = eventDeserializer,
)
