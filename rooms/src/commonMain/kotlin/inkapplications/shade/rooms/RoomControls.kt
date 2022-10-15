package inkapplications.shade.rooms

import inkapplications.shade.rooms.parameters.RoomCreateParameters
import inkapplications.shade.rooms.parameters.RoomUpdateParameters
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
     * @param id The v2 resource ID of the room to fetch data about
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

    /**
     * Update and existing room on the hue bridge.
     *
     * @param id The v2 resource ID of the room to be updated
     * @param parameters data about the room to be updated.
     */
    suspend fun updateRoom(id: ResourceId, parameters: RoomUpdateParameters): ResourceReference

    /**
     * Delete an existing room from the hue bridge.
     *
     * @param id The v2 resource ID of the room to be deleted
     */
    suspend fun deleteRoom(id: ResourceId): ResourceReference
}
