package inkapplications.shade.groupedlights

import inkapplications.shade.events.EventsModule
import inkapplications.shade.groupedlights.events.GroupedLightEvent
import inkapplications.shade.internals.InternalsModule
import inkapplications.shade.lights.events.LightEvent
import inkapplications.shade.structures.UndocumentedApi

/**
 * Provides Access to grouped light services.
 */
@OptIn(UndocumentedApi::class)
class ShadeGroupedLightsModule(
    internalsModule: InternalsModule,
    eventsModule: EventsModule,
) {
    val groupedLights: GroupedLightControls = ShadeGroupedLights(internalsModule.hueHttpClient)

    init {
        eventsModule.eventSerializerContainer.setDeserializer("grouped_light", GroupedLightEvent.serializer())
    }
}
