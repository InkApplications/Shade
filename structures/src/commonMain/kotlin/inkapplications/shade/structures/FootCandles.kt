package inkapplications.shade.structures

import inkapplications.spondee.structure.Dimension
import inkapplications.spondee.structure.div
import inkapplications.spondee.structure.format
import kotlin.jvm.JvmInline

private const val LUX_PER_FOOTCANDLE = 10.7639

/**
 * Us customary unit of illuminance.
 */
@JvmInline
value class FootCandles(override val value: Number): Illuminance, Dimension<FootCandles> {
    override fun toLux(): Lux = value.toDouble().times(LUX_PER_FOOTCANDLE).lux
    override fun withValue(value: Number): FootCandles = FootCandles(value)
    override val symbol: String get() = "fc"
    override fun toString(): String = format()
}

/**
 * Express this number as a unit in foot-candles
 */
val Number.footCandles get() = FootCandles(this)

/**
 * Convert this illuminance to foot-candles.
 */
fun Illuminance.toFootCandle(): FootCandles = toLux().div(LUX_PER_FOOTCANDLE).value.footCandles

/**
 * Convenience reference for a single foot-candle.
 */
val FootCandle = 1.footCandles
