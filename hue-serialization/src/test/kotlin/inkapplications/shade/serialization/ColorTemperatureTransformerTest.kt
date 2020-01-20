package inkapplications.shade.serialization

import inkapplications.shade.constructs.kelvin
import inkapplications.shade.constructs.mireds
import org.junit.Test

import org.junit.Assert.*

class ColorTemperatureTransformerTest {

    @Test
    fun fromJson() {
        assertEquals(200.mireds, ColorTemperatureTransformer.fromJson(200))
    }

    @Test
    fun toJson() {
        assertEquals(200, ColorTemperatureTransformer.toJson(5000.kelvin))
    }
}
