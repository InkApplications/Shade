package inkapplications.shade.rooms

import inkapplications.shade.internals.*
import inkapplications.shade.rooms.parameters.RoomCreateParameters
import inkapplications.shade.rooms.parameters.RoomUpdateParameters
import inkapplications.shade.rooms.structures.Room
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference

/**
 * Implements room controls via the hue client
 */
internal class ShadeRooms(
    private val hueHttpClient: HueHttpClient,
): RoomControls {
    override suspend fun getRoom(id: ResourceId): Room {
        return hueHttpClient.getData<List<Room>>("resource", "room", id.value).single()
    }

    override suspend fun listRooms(): List<Room> {
        return hueHttpClient.getData("resource", "room")
    }

    override suspend fun createRoom(parameters: RoomCreateParameters): ResourceReference {
        val response: List<ResourceReference> = hueHttpClient.postData(
            body = parameters,
            pathSegments = arrayOf("resource", "room"),
        )

        return response.single()
    }

    override suspend fun updateRoom(id: ResourceId, parameters: RoomUpdateParameters): ResourceReference {
        val response: List<ResourceReference> = hueHttpClient.putData(
            body = parameters,
            pathSegments =  arrayOf("resource", "room", id.value),
        )

        return response.single()
    }

    override suspend fun deleteRoom(id: ResourceId): ResourceReference {
        return hueHttpClient.deleteData<List<ResourceReference>>("resource", "room", id.value).single()
    }
}
