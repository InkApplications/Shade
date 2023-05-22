package inkapplications.shade.structures

import junit.framework.TestCase.assertTrue
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class VersionStringTest {
    @Test
    fun testVersionParsing() {
        val version = VersionString("1.2.3")

        assertEquals("1", version.major?.value)
        assertEquals("2", version.minor?.value)
        assertEquals("3", version.patch?.value)
    }

    @Test
    fun testInvalidVersion() {
        val result = runCatching {
            VersionString("asdf")
        }

        assertFalse(result.isSuccess)
        assertTrue(result.exceptionOrNull() is IllegalArgumentException)
    }
}
