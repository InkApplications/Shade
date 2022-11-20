package inkapplications.shade.core

import inkapplications.shade.events.Events
import inkapplications.shade.structures.UndocumentedApi
import shade.events.events

@UndocumentedApi
val Shade.events: Events get() = eventsModule.events
