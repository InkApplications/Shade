package inkapplications.shade.rooms

import inkapplications.shade.rooms.parameters.RoomCreateParameters
import inkapplications.shade.rooms.structures.Room
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference

/**
 * Actions to get and control rooms in the hue system
 */
interface RoomControls {
    /**
     * Get the state of a single room
     *
     * @param id The v2 resource ID of the light to fetch data about
     */
    suspend fun getRoom(id: ResourceId): Room

    /**
     * Get a list of rooms configured on the hue service.
     */
    suspend fun getRooms(): List<Room>

    /**
     * Create a new room on the hue bridge.
     */
    suspend fun createRoom(parameters: RoomCreateParameters): ResourceReference
}
