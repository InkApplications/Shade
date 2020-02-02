package inkapplications.shade.serialization

import inkapplications.shade.constructs.percentage
import org.junit.Test

import org.junit.Assert.*

class BrightnessTransformerTest {
    @Test
    fun fromJson() {
        assertEquals(1f, BrightnessTransformer.fromJson(254).fractionalValue, .01f)
        assertEquals(.5f, BrightnessTransformer.fromJson(127).fractionalValue, .01f)
        assertEquals(0f, BrightnessTransformer.fromJson(0).fractionalValue, .01f)
    }

    @Test
    fun toJson() {
        assertEquals(254, BrightnessTransformer.toJson(1f.percentage))
        assertEquals(127, BrightnessTransformer.toJson(0.5f.percentage))
        assertEquals(0, BrightnessTransformer.toJson(0f.percentage))
    }
}
