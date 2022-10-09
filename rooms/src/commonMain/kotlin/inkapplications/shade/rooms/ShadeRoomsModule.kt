package inkapplications.shade.rooms

import inkapplications.shade.internals.InternalsModule

/**
 * Provides Access to room control services.
 */
class ShadeRoomsModule(
    internalsModule: InternalsModule,
) {
    val rooms: RoomControls = ShadeRooms(internalsModule.hueHttpClient)
}

