package inkapplications.shade.serialization

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import inkapplications.shade.constructs.Brightness
import inkapplications.shade.constructs.asByteBrightness

object BrightnessTransformer {
    @FromJson
    fun fromJson(value: Int): Brightness = value.asByteBrightness

    @ToJson
    fun toJson(value: Brightness): Int = value.byteValue.toInt()
}
