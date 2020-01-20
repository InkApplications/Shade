package inkapplications.shade.serialization

import org.junit.Test

import org.junit.Assert.*
import org.threeten.bp.Duration

class DurationDeserializerTest {

    @Test
    fun fromJson() {
        assertEquals(Duration.ofMillis(400), DurationDeserializer.fromJson(4))
    }

    @Test
    fun toJson() {
        assertEquals(10, DurationDeserializer.toJson(Duration.ofSeconds(1)))
    }
}
