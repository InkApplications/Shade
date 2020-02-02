package inkapplications.shade.serialization

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import inkapplications.shade.constructs.Percentage
import inkapplications.shade.constructs.bytePercentage

object BrightnessTransformer {
    @FromJson
    fun fromJson(value: Int): Percentage = value.bytePercentage

    @ToJson
    fun toJson(value: Percentage): Int = value.byteValue.toInt()
}
