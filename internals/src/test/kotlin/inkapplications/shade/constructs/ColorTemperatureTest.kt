package inkapplications.shade.constructs

import org.junit.Test

import org.junit.Assert.*

class ColorTemperatureTest {
    @Test
    fun testKelvinConversion() {
        assertEquals(200.mireds, 5000.kelvin)
        assertEquals(500, 2000.kelvin.miredValue)
        assertEquals(40, 25_000.kelvin.miredValue)
    }

    @Test
    fun testMiredConversion() {
        assertEquals(5000.kelvin, 200.mireds)
        assertEquals(2000, 500.mireds.kelvinValue)
        assertEquals(25_000, 40.mireds.kelvinValue)
    }
}
