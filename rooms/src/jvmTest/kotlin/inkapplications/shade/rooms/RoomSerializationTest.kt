package inkapplications.shade.rooms

import inkapplications.shade.rooms.structures.Room
import inkapplications.shade.rooms.structures.RoomArchetype
import inkapplications.shade.structures.ResourceType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals

class RoomSerializationTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun fullJsonDeserialization() {
        val data = """
            {
                "id": "test-id",
                "id_v1": "/groups/4",
                "children": [
                    {"rid":"test-id-2","rtype":"device"},
                    {"rid":"test-id-3","rtype":"device"}
                ],
                "services": [
                    {"rid":"test-id-4","rtype":"grouped_light"}
                ],
                "metadata": {
                    "name":"Hallway",
                    "archetype":"other"
                },
                "type":"room"
            }
        """.trimIndent()

        val room = json.decodeFromString<Room>(data)

        assertEquals("test-id", room.id.value)
        assertEquals("Hallway", room.metadata.name)
        assertEquals(RoomArchetype.Other, room.metadata.archetype)
        assertEquals(2, room.children.size)
        assertEquals("test-id-2", room.children[0].id.value)
        assertEquals(ResourceType.Device, room.children[0].type)
        assertEquals("test-id-3", room.children[1].id.value)
        assertEquals(ResourceType.Device, room.children[1].type)
        assertEquals(1, room.services.size)
        assertEquals("test-id-4", room.services[0].id.value)
        assertEquals(ResourceType.GroupedLight, room.services[0].type)
    }

    @Test
    fun minimalSerialization() {
        val data = """
            {
                "id": "test-id",
                "children": [],
                "services": [],
                "metadata": {
                    "name":"Hallway",
                    "archetype":"other"
                }
            }
        """.trimIndent()

        val room = json.decodeFromString<Room>(data)

        assertEquals("test-id", room.id.value)
        assertEquals("Hallway", room.metadata.name)
        assertEquals(RoomArchetype.Other, room.metadata.archetype)
        assertEquals(0, room.children.size)
        assertEquals(0, room.services.size)
    }
}
