package inkapplications.shade.devices

import inkapplications.shade.devices.structures.Device
import inkapplications.shade.devices.structures.ProductArchetype
import inkapplications.shade.structures.ResourceType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DeviceSerializationTest {
    val json = Json { ignoreUnknownKeys = true; prettyPrint = true }

    @Test
    fun fullDeserialization() {
        val data = """
            {
                "id": "test-id",
                "id_v1": "/lights/2",
                "product_data": {
                    "model_id": "TEST-MODEL",
                    "manufacturer_name": "Test Manufacturer",
                    "product_name": "Test Product",
                    "product_archetype": "sultan_bulb",
                    "certified": true,
                    "software_version": "1.2.3",
                    "hardware_platform_type": "123abc"
                },
                "metadata": {
                    "name": "Test name",
                    "archetype": "classic_bulb"
                },
                "services": [
                    {
                        "rid": "test-id",
                        "rtype": "light"
                    }
                ],
                "type": "device"
            }
        """.trimIndent()

        val device = json.decodeFromString<Device>(data)

        assertEquals("test-id", device.id.value)
        assertEquals("/lights/2", device.v1Id)
        assertEquals("TEST-MODEL", device.productData.modelId.value)
        assertEquals("Test Manufacturer", device.productData.manufacturerName)
        assertEquals("Test Product", device.productData.productName)
        assertEquals(ProductArchetype.SultanBulb, device.productData.productArchetype)
        assertEquals(true, device.productData.certified)
        assertEquals("1.2.3", device.productData.softwareVersion.full)
        assertEquals("123abc", device.productData.hardwarePlatformType?.value)
        assertEquals("Test name", device.metadata.name)
        assertEquals(ProductArchetype.ClassicBulb, device.metadata.archetype)
        assertEquals("test-id", device.services.single().id.value)
        assertEquals(ResourceType.Light, device.services.single().type)
    }

    @Test
    fun minimalJsonDeserialization() {
        val data = """
            {
                "id": "test-id",
                "product_data": {
                    "model_id": "TEST-MODEL",
                    "manufacturer_name": "Test Manufacturer",
                    "product_name": "Test Product",
                    "product_archetype": "sultan_bulb",
                    "certified": true,
                    "software_version": "1.2.3"
                },
                "metadata": {
                    "name": "Test name",
                    "archetype": "classic_bulb"
                },
                "services": [
                    {
                        "rid": "test-id",
                        "rtype": "light"
                    }
                ],
                "type": "device"
            }
        """.trimIndent()

        val device = json.decodeFromString<Device>(data)

        assertEquals("test-id", device.id.value)
        assertNull(device.v1Id)
        assertEquals("TEST-MODEL", device.productData.modelId.value)
        assertEquals("Test Manufacturer", device.productData.manufacturerName)
        assertEquals("Test Product", device.productData.productName)
        assertEquals(ProductArchetype.SultanBulb, device.productData.productArchetype)
        assertEquals(true, device.productData.certified)
        assertEquals("1.2.3", device.productData.softwareVersion.full)
        assertNull(device.productData.hardwarePlatformType)
        assertEquals("Test name", device.metadata.name)
        assertEquals(ProductArchetype.ClassicBulb, device.metadata.archetype)
        assertEquals("test-id", device.services.single().id.value)
        assertEquals(ResourceType.Light, device.services.single().type)
    }
}
