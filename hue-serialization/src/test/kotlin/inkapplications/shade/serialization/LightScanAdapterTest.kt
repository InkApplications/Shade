package inkapplications.shade.serialization

import com.squareup.moshi.Moshi
import inkapplications.shade.constructs.Scan
import inkapplications.shade.constructs.ScanReference
import org.junit.Test

import org.junit.Assert.*
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

class LightScanAdapterTest {
    val moshi = Moshi.Builder()
        .add(LightScanAdapter)
        .add(InstantDeserializer)
        .build()

    val adapter = moshi.adapter(Scan::class.java)

    @Test fun scanResults() {
        val example = """
{
    "7": {"name": "Hue Lamp 7"},
    "8": {"name": "Hue Lamp 8"},
    "lastscan": "2012-10-29T12:00:00"
}
        """

        val result = adapter.fromJson(example)


        assertEquals(2, result?.lights?.size)
        assertTrue(result?.lights?.contains(ScanReference("7", "Hue Lamp 7")) ?: false)
        assertTrue(result?.lights?.contains(ScanReference("8", "Hue Lamp 8")) ?: false)
        assertEquals(LocalDateTime.parse("2012-10-29T12:00:00").toInstant(ZoneOffset.UTC), result?.lastScan)
    }

    @Test fun scanNoResults() {
        val example = """
{
    "lastscan": "2012-10-29T12:00:00"
}
        """

        val result = adapter.fromJson(example)

        assertTrue(result?.lights?.isEmpty() ?: false)
        assertEquals(LocalDateTime.parse("2012-10-29T12:00:00").toInstant(ZoneOffset.UTC), result?.lastScan)
    }

    @Test fun noScans() {
        val example = """
{
    "lastscan": "none"
}
        """

        val result = adapter.fromJson(example)

        assertTrue(result?.lights?.isEmpty() ?: false)
        assertNull(result?.lastScan)
    }
}
