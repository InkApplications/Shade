package inkapplications.shade.hueclient.structures

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson

/**
 * X/Y coordinates.
 *
 * These are used throughout the API to convey Gamut locations.
 */
@JsonClass(generateAdapter = true)
data class Coordinates(val x: Float, val y: Float)

/**
 * Deserialize a list of coordinates into XY coordinates.
 */
internal object CoordinatesListDeserializer {
    @FromJson fun fromJson(coordinates: List<Float>): Coordinates = Coordinates(coordinates[0], coordinates[1])
    @ToJson fun fromJson(coordinates: Coordinates): List<Float> = listOf(coordinates.x, coordinates.y)
}
