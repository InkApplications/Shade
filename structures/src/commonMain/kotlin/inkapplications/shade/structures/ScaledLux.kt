package inkapplications.shade.structures

import inkapplications.spondee.structure.Dimension
import inkapplications.spondee.structure.format
import inkapplications.spondee.structure.toDouble
import kotlin.jvm.JvmInline
import kotlin.math.log10
import kotlin.math.pow

private const val SCALE_FACTOR = 10000.0
private const val OFFSET = 1.0

/**
 * Hue's custom logarithmic illuminance unit.
 * Light level in 10000*log10(lux) + 1.
 *
 * Logarithmic scale used because the human eye adjusts to light levels
 * and small changes at low lux levels are more noticeable than at high
 * lux levels. This allows use of linear scale configuration sliders.
 */
@JvmInline
value class ScaledLux(override val value: Number) : Illuminance, Dimension<ScaledLux> {
    override fun toLux(): Lux {
        val logLux = (value.toDouble() - OFFSET) / SCALE_FACTOR
        return 10.0.pow(logLux).lux
    }
    override fun withValue(value: Number): ScaledLux = ScaledLux(value)
    override val symbol: String get() = "scaled lx"
    override fun toString(): String = format()
}

/**
 * Express this number as a Hue LogLux unit.
 */
val Number.scaledLux get() = ScaledLux(this)

/**
 * Convert this illuminance to Hue LogLux.
 */
fun Illuminance.toScaledLux(): ScaledLux {
    return toLux().toDouble().let(::log10)
        .times(SCALE_FACTOR).plus(OFFSET).scaledLux
}
