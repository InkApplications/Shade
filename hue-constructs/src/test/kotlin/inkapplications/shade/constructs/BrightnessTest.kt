package inkapplications.shade.constructs

import org.junit.Test

import org.junit.Assert.*

class BrightnessTest {
    @Test
    fun testConversionsMaxValue() {
        assertEquals(254.toUByte(), 1.0.percentageBrightness.byteValue)
        assertEquals(254.toUByte(), 1.0f.percentageBrightness.byteValue)
        assertEquals(254.toUByte(), 254.asByteBrightness.byteValue)
        assertEquals(254.toUByte(), 254.toUByte().asBrightness.byteValue)
    }

    @Test
    fun testConversionsMinValue() {
        assertEquals(0.toUByte(), 0.0.percentageBrightness.byteValue)
        assertEquals(0.toUByte(), 0.0f.percentageBrightness.byteValue)
        assertEquals(0.toUByte(), 0.asByteBrightness.byteValue)
        assertEquals(0.toUByte(), 0.toUByte().asBrightness.byteValue)
    }

    @Test
    fun testConversionsMidValue() {
        assertEquals(127.toUByte(), 0.5.percentageBrightness.byteValue)
        assertEquals(127.toUByte(), 0.5f.percentageBrightness.byteValue)
        assertEquals(127.toUByte(), 127.asByteBrightness.byteValue)
        assertEquals(127.toUByte(), 127.toUByte().asBrightness.byteValue)
    }

    @Test
    fun testPercentConversions() {
        assertEquals(.5f, Brightness(127.toUByte()).percentageValue, .01f)
        assertEquals(1f, Brightness(254.toUByte()).percentageValue, .01f)
        assertEquals(0f, Brightness(0.toUByte()).percentageValue, .01f)
    }
}
