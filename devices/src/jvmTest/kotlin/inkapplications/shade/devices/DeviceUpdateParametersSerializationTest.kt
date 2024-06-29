package inkapplications.shade.devices

import inkapplications.shade.devices.parameters.DeviceMetadataParameters
import inkapplications.shade.devices.parameters.IdentifyParameters
import inkapplications.shade.devices.parameters.UpdateDeviceParameters
import inkapplications.shade.devices.structures.ProductArchetype
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class DeviceUpdateParametersSerializationTest {
    private val json = Json { ignoreUnknownKeys = true; prettyPrint = true }

    @Test
    fun serializeFull() {
        val data = """
            {
                "metadata": {
                    "name": "Test Device",
                    "archetype": "classic_bulb"
                },
                "identify": {
                    "action": "identify"
                }
            }
        """.trimIndent()
        val parameters = UpdateDeviceParameters(
            metadata = DeviceMetadataParameters(
                name = "Test Device",
                archetype = ProductArchetype.ClassicBulb,
            ),
            identify = IdentifyParameters(),
        )

        val serialized = json.encodeToString(parameters)

        assertEquals(data, serialized)
    }

    @Test
    fun serializeMinimal() {
        val data = """
            {}
        """.trimIndent()

        val parameters = UpdateDeviceParameters()

        val serialized = json.encodeToString(parameters)

        assertEquals(data, serialized)
    }
}
