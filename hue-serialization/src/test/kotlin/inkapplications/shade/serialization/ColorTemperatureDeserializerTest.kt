package inkapplications.shade.serialization

import inkapplications.shade.constructs.kelvin
import inkapplications.shade.constructs.mireds
import org.junit.Test

import org.junit.Assert.*

class ColorTemperatureDeserializerTest {

    @Test
    fun fromJson() {
        assertEquals(200.mireds, ColorTemperatureDeserializer.fromJson(200))
    }

    @Test
    fun toJson() {
        assertEquals(200, ColorTemperatureDeserializer.toJson(5000.kelvin))
    }
}
