package inkapplications.shade.serialization

import inkapplications.shade.constructs.percentageBrightness
import org.junit.Test

import org.junit.Assert.*

class BrightnessTransformerTest {
    @Test
    fun fromJson() {
        assertEquals(1f, BrightnessTransformer.fromJson(254).percentageValue, .01f)
        assertEquals(.5f, BrightnessTransformer.fromJson(127).percentageValue, .01f)
        assertEquals(0f, BrightnessTransformer.fromJson(0).percentageValue, .01f)
    }

    @Test
    fun toJson() {
        assertEquals(254, BrightnessTransformer.toJson(1f.percentageBrightness))
        assertEquals(127, BrightnessTransformer.toJson(0.5f.percentageBrightness))
        assertEquals(0, BrightnessTransformer.toJson(0f.percentageBrightness))
    }
}
