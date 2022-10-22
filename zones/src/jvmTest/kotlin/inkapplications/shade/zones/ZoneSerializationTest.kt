package inkapplications.shade.zones

import inkapplications.shade.structures.ResourceType
import inkapplications.shade.structures.RoomArchetype
import inkapplications.shade.zones.structures.Zone
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
                "id_v1": "/zone/4",
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
                "type":"zone"
            }
        """.trimIndent()

        val zone = json.decodeFromString<Zone>(data)

        assertEquals("test-id", zone.id.value)
        assertEquals("Hallway", zone.metadata.name)
        assertEquals(RoomArchetype.Other, zone.metadata.archetype)
        assertEquals(2, zone.children.size)
        assertEquals("test-id-2", zone.children[0].id.value)
        assertEquals(ResourceType.Device, zone.children[0].type)
        assertEquals("test-id-3", zone.children[1].id.value)
        assertEquals(ResourceType.Device, zone.children[1].type)
        assertEquals(1, zone.services.size)
        assertEquals("test-id-4", zone.services[0].id.value)
        assertEquals(ResourceType.GroupedLight, zone.services[0].type)
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

        val zone = json.decodeFromString<Zone>(data)

        assertEquals("test-id", zone.id.value)
        assertEquals("Hallway", zone.metadata.name)
        assertEquals(RoomArchetype.Other, zone.metadata.archetype)
        assertEquals(0, zone.children.size)
        assertEquals(0, zone.services.size)
    }
}