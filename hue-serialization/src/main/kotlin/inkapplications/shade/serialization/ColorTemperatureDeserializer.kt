package inkapplications.shade.serialization

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import inkapplications.shade.constructs.ColorTemperature
import inkapplications.shade.constructs.mireds

/**
 * Convert custom color temperature units to/from the API's mired integer.
 */
object ColorTemperatureDeserializer {
    @FromJson
    fun fromJson(value: Int): ColorTemperature = value.mireds

    @ToJson
    fun toJson(value: ColorTemperature): Int = value.miredValue
}
