package inkapplications.shade.serialization

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter

/**
 * Deserialize dates as 310 Instant objects.
 */
object InstantDeserializer {
    @FromJson fun fromJson(time: String): Instant = LocalDateTime.parse(time).toInstant(ZoneOffset.UTC)
    @ToJson fun toJson(time: Instant): String = DateTimeFormatter.ISO_DATE_TIME.format(time)
}
