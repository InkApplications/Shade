package inkapplications.shade.serialization

import com.github.ajalt.colormath.Color
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import inkapplications.shade.constructs.Coordinates

object ColorTransformer {
    @ToJson
    fun toCoordinates(color: Color) = Coordinates(color.toXYZ())

    @FromJson
    fun fromCoordinates(coordinates: Coordinates) = coordinates
}
