package inkapplications.shade.constructs

import com.squareup.moshi.JsonClass
import org.threeten.bp.Instant

@JsonClass(generateAdapter = true)
data class ScanReference(val id: String, val name: String)

/**
 * Results for a new device scan.
 *
 * @param lastScan The timestamp that the scan was last updated
 * @param
 */
@JsonClass(generateAdapter = true)
data class Scan(
    val lastScan: Instant?,
    val lights: List<ScanReference>
)
