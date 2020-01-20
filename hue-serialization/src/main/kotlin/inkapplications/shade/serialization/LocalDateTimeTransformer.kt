package inkapplications.shade.serialization

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Deserialize dates as 310 Instant objects.
 */
object LocalDateTimeTransformer {
    @FromJson fun fromJson(time: String): LocalDateTime = LocalDateTime.parse(time)
    @ToJson fun toJson(time: LocalDateTime): String = DateTimeFormatter.ISO_DATE_TIME.format(time)
}
