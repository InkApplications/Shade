package inkapplications.shade.rooms

import inkapplications.shade.rooms.structures.Room
import inkapplications.shade.structures.ResourceId

/**
 * Actions to get and control rooms in the hue system
 */
interface RoomControls {
    /**
     * Get the state of a single room
     */
    suspend fun getRoom(id: ResourceId): Room

    /**
     * Get a list of rooms configured on the hue service.
     */
    suspend fun getRooms(): List<Room>
}
