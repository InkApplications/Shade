package inkapplications.shade.lights

import inkapplications.shade.internals.InternalsModule
import inkapplications.shade.lights.events.LightEvent
import inkapplications.shade.structures.UndocumentedApi
import inkapplications.shade.events.EventsModule

/**
 * Provides Access to Light control services.
 */
@OptIn(UndocumentedApi::class)
class ShadeLightsModule(
    internalsModule: InternalsModule,
    eventsModule: EventsModule,
) {
    val lights: LightControls = ShadeLights(internalsModule.hueHttpClient)

    init {
        eventsModule.eventSerializerContainer.setDeserializer("light", LightEvent.serializer())
    }
}
