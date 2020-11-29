package inkapplications.shade.constructs

import com.github.ajalt.colormath.Color
import com.squareup.moshi.JsonClass

/**
 * X/Y in a CIE xyY coordinate.
 *
 * These are used throughout the API to convey Gamut locations.
 */
@JsonClass(generateAdapter = true)
data class Coordinates(val x: Float, val y: Float) {
    constructor(color: Color): this(
        x = color.xyy().first.toFloat(),
        y = color.xyy().second.toFloat()
    )
}

/**
 * Convert CIE XYZ to an xyY Chromaticity.
 *
 * In the case that this is black. The color will default to a D65 White reference.
 */
private fun Color.xyy(default: Pair<Double, Double> = 0.31271 to 0.32902): Pair<Double, Double> {
    with(toXYZ()) {
        val sum = x + y + z
        if (sum == 0.0) return default

        return (x / sum) to (y / sum)
    }
}
