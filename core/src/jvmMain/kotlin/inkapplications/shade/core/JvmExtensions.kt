package inkapplications.shade.core

import inkapplications.shade.structures.UndocumentedApi
import inkapplications.shade.events.Events
import shade.events.events

@UndocumentedApi
val Shade.events: Events get() = eventsModule.events
