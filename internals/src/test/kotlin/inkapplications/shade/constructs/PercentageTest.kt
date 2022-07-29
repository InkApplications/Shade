package inkapplications.shade.constructs

import org.junit.Test

import org.junit.Assert.*

class PercentageTest {
    @Test
    fun testConversionsMaxValue() {
        assertEquals(254.toUByte(), 1.0.asPercentage.byteValue)
        assertEquals(254.toUByte(), 1.0f.asPercentage.byteValue)
        assertEquals(254.toUByte(), 254.bytePercentage.byteValue)
        assertEquals(254.toUByte(), 254.toUByte().bytePercentage.byteValue)
        assertEquals(254.toUByte(), 100.percent.byteValue)
    }

    @Test
    fun testConversionsMinValue() {
        assertEquals(0.toUByte(), 0.0.asPercentage.byteValue)
        assertEquals(0.toUByte(), 0.0f.asPercentage.byteValue)
        assertEquals(0.toUByte(), 0.bytePercentage.byteValue)
        assertEquals(0.toUByte(), 0.toUByte().bytePercentage.byteValue)
        assertEquals(0.toUByte(), 0.percent.byteValue)
    }

    @Test
    fun testConversionsMidValue() {
        assertEquals(127.toUByte(), 0.5.asPercentage.byteValue)
        assertEquals(127.toUByte(), 0.5f.asPercentage.byteValue)
        assertEquals(127.toUByte(), 127.bytePercentage.byteValue)
        assertEquals(127.toUByte(), 127.toUByte().bytePercentage.byteValue)
        assertEquals(127.toUByte(), 50.percent.byteValue)
    }

    @Test
    fun testPercentConversions() {
        assertEquals(.5f, Percentage(127.toUByte()).fractionalValue, .01f)
        assertEquals(1f, Percentage(254.toUByte()).fractionalValue, .01f)
        assertEquals(0f, Percentage(0.toUByte()).fractionalValue, .01f)
    }
}
