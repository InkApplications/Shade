package inkapplications.shade.serialization

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import inkapplications.shade.constructs.Coordinates

/**
 * Deserialize a list of coordinates into XY coordinates.
 */
object CoordinatesListDeserializer {
    @FromJson fun fromJson(coordinates: List<Float>): Coordinates = Coordinates(coordinates[0], coordinates[1])
    @ToJson fun fromJson(coordinates: Coordinates): List<Float> = listOf(coordinates.x, coordinates.y)
}
