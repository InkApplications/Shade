package inkapplications.shade.rooms

import inkapplications.shade.rooms.parameters.RoomCreateParameters
import inkapplications.shade.structures.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals

class RoomCreateParametersSerializationTest {
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

        val parameters = RoomCreateParameters(
            children = listOf(
                ResourceReference(ResourceId("test-id-2"), ResourceType.Device),
                ResourceReference(ResourceId("test-id-3"), ResourceType.Device),
            ),
            metadata = RoomMetadata(
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
                "metadata": {
                    "archetype": "other",
                    "name": "Hallway"
                },
                "children": [
                ]
            }
        """.trimIndent()

        val parameters = RoomCreateParameters(
            children = listOf(),
            metadata = RoomMetadata(
                name = "Hallway",
                archetype = RoomArchetype.Other,
            ),
        )

        val serialized = json.encodeToString(parameters)

        assertEquals(data, serialized)
    }
}
