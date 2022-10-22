package inkapplications.shade.rooms

import inkapplications.shade.rooms.parameters.RoomUpdateParameters
import inkapplications.shade.rooms.parameters.RoomMetadataUpdateParameters
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.ResourceType
import inkapplications.shade.structures.RoomArchetype
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals

class RoomUpdateParametersSerializationTest {
    val json = Json { ignoreUnknownKeys = true; prettyPrint = true }

    @Test
    fun fullJsonDeserialization() {
        val data = """
            {
                "metadata": {
                    "archetype": "other",
                    "name": "Hallway"
                },
                "children": [
                    {
                        "rid": "test-id-2",
                        "rtype": "device"
                    },
                    {
                        "rid": "test-id-3",
                        "rtype": "device"
                    }
                ]
            }
        """.trimIndent()

        val parameters = RoomUpdateParameters(
            children = listOf(
                ResourceReference(ResourceId("test-id-2"), ResourceType.Device),
                ResourceReference(ResourceId("test-id-3"), ResourceType.Device),
            ),
            metadata = RoomMetadataUpdateParameters(
                name = "Hallway",
                archetype = RoomArchetype.Other,
            ),
        )

        val serialized = json.encodeToString(parameters)

        assertEquals(data, serialized)
    }

    @Test
    fun minimalSerialization() {
        val data = """
            {
            }
        """.trimIndent()

        val parameters = RoomUpdateParameters()

        val serialized = json.encodeToString(parameters)

        assertEquals(data, serialized)
    }
}
