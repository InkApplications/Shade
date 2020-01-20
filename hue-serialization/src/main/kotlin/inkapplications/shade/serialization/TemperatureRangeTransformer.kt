package inkapplications.shade.serialization
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import inkapplications.shade.constructs.ColorTemperature
import inkapplications.shade.constructs.mireds

@JsonClass(generateAdapter = true)
data class MinMax(val min: Int, val max: Int)

object TemperatureRangeTransformer {
    @FromJson fun fromJson(range: MinMax): ClosedRange<ColorTemperature> = range.min.mireds..range.max.mireds
    @ToJson fun toJson(range: ClosedRange<ColorTemperature>): MinMax = MinMax(range.start.miredValue, range.endInclusive.miredValue)
}
