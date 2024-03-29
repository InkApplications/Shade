package inkapplications.shade.groupedlights

import inkapplications.shade.groupedlights.structures.GroupedLight
import inkapplications.shade.lights.structures.AlertEffectType
import inkapplications.shade.structures.ResourceType
import inkapplications.spondee.scalar.percent
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GroupedLightSerializationTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun fullJsonDeserialization() {
        val data = """
            {
                "id": "test-id",
                "id_v1": "/grouped_light/4",
                "on": {
                    "on": true
                },
                "owner": {
                   "rid": "test-owner-id",
                   "rtype": "room"
                },
                "dimming": {
                    "brightness": 12.3
                }
                "alert": {
                    "action_values": ["breathe"]
                },
                "type":"grouped_light"
            }
        """.trimIndent()

        val group = json.decodeFromString<GroupedLight>(data)

        assertEquals("test-id", group.id.value)
        assertEquals("test-owner-id", group.owner.id.value)
        assertEquals(ResourceType.Room, group.owner.type)
        assertEquals(true, group.powerInfo?.on)
        assertEquals(12.3.percent, group.dimmingInfo?.brightness)
        assertEquals(listOf(AlertEffectType.Breathe), group.alertInfo?.actionValues)
    }

    @Test
    fun minimalSerialization() {
        val data = """
            {
                "id": "test-id",
                "id_v1": "/grouped_light/4",
                "type":"grouped_light",
                "owner": {
                   "rid": "test-owner-id",
                   "rtype": "room"
                }
            }
        """.trimIndent()

        val group = json.decodeFromString<GroupedLight>(data)

        assertEquals("test-id", group.id.value)
        assertEquals("test-owner-id", group.owner.id.value)
        assertEquals(ResourceType.Room, group.owner.type)
        assertNull(group.powerInfo)
        assertNull(group.alertInfo)
    }
}
