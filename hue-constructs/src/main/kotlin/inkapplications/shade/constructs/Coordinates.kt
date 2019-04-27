package inkapplications.shade.constructs

import com.squareup.moshi.JsonClass

/**
 * X/Y coordinates.
 *
 * These are used throughout the API to convey Gamut locations.
 */
@JsonClass(generateAdapter = true)
data class Coordinates(val x: Float, val y: Float)
