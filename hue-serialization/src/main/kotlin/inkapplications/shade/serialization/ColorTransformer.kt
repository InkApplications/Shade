package inkapplications.shade.serialization

import com.github.ajalt.colormath.ConvertibleColor
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import inkapplications.shade.constructs.Coordinates

object ColorTransformer {
    @ToJson
    fun toCoordinates(color: ConvertibleColor) = Coordinates(color.toXYZ())

    @FromJson
    fun fromCoordinates(coordinates: Coordinates) = coordinates
}
