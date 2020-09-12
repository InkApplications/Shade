package inkapplications.shade.lights

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test
import org.threeten.bp.Instant

class HueUpdateStateTransformerTest {
    @Test
    fun fromHueFormat() {
        val now = Instant.now()
        val given = HueUpdateState("fake", now)

        val result = HueUpdateStateTransformer.fromHueFormat(given)

        assertEquals("State is not modified", "fake", result.state)
        assertEquals("Last install is used in nullable", now, result.lastKnownInstall)
        assertEquals("Last install is used in non-nullable field", now, result.lastInstall)
    }

    @Test
    fun fromNullHueFormat() {
        val given = HueUpdateState("fake", null)

        val result = HueUpdateStateTransformer.fromHueFormat(given)

        assertEquals("State is not modified", "fake", result.state)
        assertNull("Nullable field should reflect null last install", result.lastKnownInstall)
        assertEquals("Non-nullable field should use MIN as timestamp", Instant.MIN, result.lastInstall)
    }

    @Test
    fun toHueFormatCompat() {
        val now = Instant.now()
        val given = UpdateState("fake", now)

        val result = HueUpdateStateTransformer.toHueFormat(given)

        assertEquals("State is not modified", "fake", result.state)
        assertEquals("Nullable last install should be passed along", now, result.lastInstall)
    }

    @Test
    fun toHueFormatCompatSpecified() {
        val now = Instant.now()
        val given = UpdateState("fake", Instant.MIN, now)

        val result = HueUpdateStateTransformer.toHueFormat(given)

        assertEquals("State is not modified", "fake", result.state)
        assertEquals("Nullable field should take precedent if specified", now, result.lastInstall)
    }

    @Test
    fun toHueFormatCompatNull() {
        val now = Instant.now()
        val given = UpdateState("fake", now, null)

        val result = HueUpdateStateTransformer.toHueFormat(given)

        assertEquals("State is not modified", "fake", result.state)
        assertNull("Nullable field should take precedent if specified, even if null", result.lastInstall)
    }

    @Test
    fun toHueFormatCompatNullFromMin() {
        val given = UpdateState("fake", Instant.MIN)

        val result = HueUpdateStateTransformer.toHueFormat(given)

        assertEquals("State is not modified", "fake", result.state)
        assertNull("Min instants should be interpreted as null for compatibility", result.lastInstall)
    }
}
