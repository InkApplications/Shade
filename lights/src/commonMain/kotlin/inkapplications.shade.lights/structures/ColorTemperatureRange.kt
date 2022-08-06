package inkapplications.shade.lights.structures

import inkapplications.spondee.measure.ColorTemperature
import inkapplications.spondee.measure.Kelvin
import inkapplications.spondee.measure.Mireds
import inkapplications.spondee.measure.toTemperature
import inkapplications.spondee.structure.value
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
    val kelvinRange = warmest.toTemperature().value(Kelvin).roundToLong()..coolest.toTemperature().value(Kelvin).roundToLong()

    /**
     * Express the color temperatures as a range of Mireds.
     */
    val miredRange = coolest.value(Mireds).roundToInt()..warmest.value(Mireds).roundToInt()

    operator fun contains(other: ColorTemperature): Boolean {
        val warmestKelvin = warmest.toTemperature().value(Kelvin)
        val coolestKelvin = coolest.toTemperature().value(Kelvin)
        val otherKelvin = other.toTemperature().value(Kelvin)

        return otherKelvin in warmestKelvin..coolestKelvin
    }

    override fun toString(): String {
        return "${Kelvin.format(warmest.toTemperature())}..${Kelvin.format(coolest.toTemperature())}"
    }
}
