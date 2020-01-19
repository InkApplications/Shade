package inkapplications.shade.serialization

import inkapplications.shade.constructs.percentageBrightness
import org.junit.Test

import org.junit.Assert.*

class BrightnessDeserializerTest {
    @Test
    fun fromJson() {
        assertEquals(1f, BrightnessDeserializer.fromJson(254).percentageValue, .01f)
        assertEquals(.5f, BrightnessDeserializer.fromJson(127).percentageValue, .01f)
        assertEquals(0f, BrightnessDeserializer.fromJson(0).percentageValue, .01f)
    }

    @Test
    fun toJson() {
        assertEquals(254, BrightnessDeserializer.toJson(1f.percentageBrightness))
        assertEquals(127, BrightnessDeserializer.toJson(0.5f.percentageBrightness))
        assertEquals(0, BrightnessDeserializer.toJson(0f.percentageBrightness))
    }
}
