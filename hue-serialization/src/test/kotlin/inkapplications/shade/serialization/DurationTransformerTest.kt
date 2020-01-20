package inkapplications.shade.serialization

import org.junit.Test

import org.junit.Assert.*
import org.threeten.bp.Duration

class DurationTransformerTest {

    @Test
    fun fromJson() {
        assertEquals(Duration.ofMillis(400), DurationTransformer.fromJson(4))
    }

    @Test
    fun toJson() {
        assertEquals(10, DurationTransformer.toJson(Duration.ofSeconds(1)))
    }
}
