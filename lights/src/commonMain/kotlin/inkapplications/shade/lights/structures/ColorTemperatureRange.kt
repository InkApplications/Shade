package inkapplications.shade.lights.structures

import inkapplications.spondee.measure.ColorTemperature
import inkapplications.spondee.measure.toMireds
import inkapplications.spondee.structure.convert
import kotlin.math.roundToInt
import kotlin.math.roundToLong

/**
 * Represents a range of color temperatures.
 */
data class ColorTemperatureRange(
    val coolest: ColorTemperature,
    val warmest: ColorTemperature,
) {
    /**
     * Express the color temperatures as a range of Kelvin units.
     */
    val kelvinRange = warmest.toKelvin().convert { roundToLong() }..coolest.toKelvin().convert { roundToLong() }

    /**
     * Express the color temperatures as a range of Mireds.
     */
    val miredRange = coolest.toMireds().convert { roundToInt() }..warmest.toMireds().convert { roundToInt() }

    operator fun contains(other: ColorTemperature): Boolean {
        val warmestKelvin = warmest.toKelvin().value.toDouble()
        val coolestKelvin = coolest.toKelvin().value.toDouble()
        val otherKelvin = other.toKelvin().value.toDouble()

        return otherKelvin in warmestKelvin..coolestKelvin
    }

    override fun toString(): String {
        return "$warmest..$coolest"
    }
}
