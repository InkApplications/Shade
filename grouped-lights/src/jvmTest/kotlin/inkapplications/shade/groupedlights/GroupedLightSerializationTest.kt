package inkapplications.shade.groupedlights

import inkapplications.shade.groupedlights.structures.GroupedLight
import inkapplications.shade.structures.AlertEffectType
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
                "alert": {
                    "action_values": ["breathe"]
                },
                "type":"grouped_light"
            }
        """.trimIndent()

        val group = json.decodeFromString<GroupedLight>(data)

        assertEquals("test-id", group.id.value)
        assertEquals(true, group.powerInfo?.on)
        assertEquals(listOf(AlertEffectType.Breathe), group.alertInfo?.actionValues)
    }

    @Test
    fun minimalSerialization() {
        val data = """
            {
                "id": "test-id",
                "id_v1": "/grouped_light/4",
                "type":"grouped_light"
            }
        """.trimIndent()

        val group = json.decodeFromString<GroupedLight>(data)

        assertEquals("test-id", group.id.value)
        assertNull(group.powerInfo)
        assertNull(group.alertInfo)
    }
}
