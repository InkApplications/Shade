package inkapplications.shade.serialization

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.Duration

object DurationDeserializer {
    @FromJson
    fun fromJson(value: Long): Duration = Duration.ofMillis(value * 100)

    @ToJson
    fun toJson(value: Duration): Long = value.toMillis() / 100
}
