package inkapplications.shade.rooms

import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.rooms.structures.Room
import inkapplications.shade.structures.ResourceId

/**
 * Implements room controls via the hue client
 */
internal class ShadeRooms(
    private val hueHttpClient: HueHttpClient,
): RoomControls {
    override suspend fun getRoom(id: ResourceId): Room {
        return hueHttpClient.getData<List<Room>>("resource", "room", id.value).single()
    }
}
