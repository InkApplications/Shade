package inkapplications.shade.constructs

private const val MAX_VALUE = 254

/**
 * Unit of percentage stored as an unsigned byte.
 *
 * @param byteValue an unsigned byte value, unit for the Hue API.
 */
data class Percentage internal constructor(val byteValue: UByte): Comparable<Percentage> {
    val fractionalValue: Float = byteValue.toFloat() / MAX_VALUE.toFloat()

    override fun compareTo(other: Percentage): Int = byteValue.compareTo(other.byteValue)
}

/**
 * Express with a fractional percentage.
 *
 * This is a fraction value, not a whole percentage!
 * ex: `0.55f.asPercentage` is 55%
 *
 * @see percent to convert a whole percentage value.
 */
val Float.asPercentage get() = Percentage((MAX_VALUE.toFloat() * this.toDouble()).toInt().toUByte())

/**
 * Express with a fractional percentage.
 *
 * This is a fraction value, not a whole percentage!
 * ex: `0.55f.asPercentage` is 55%
 *
 * @see percent to convert a whole percentage value.
 */
val Double.asPercentage get() = Percentage((MAX_VALUE.toFloat() * this).toInt().toUByte())

/**
 * Express a percentage as a whole number.
 *
 * This is a whole number, not a fraction!
 * ex. `55.percent` is 55% or 0.55
 *
 * @see asPercentage to convert a fractional value such as `.55`
 */
val Number.percent get() = (this.toFloat() / 100f).asPercentage

/**
 * Convert a 0-254 byte value into a percentage value.
 *
 * This is Hue's native value system. It's unlikely you'd want to use this
 * unless you are working with other hue systems.
 */
val Number.bytePercentage get() = Percentage(this.toByte().toUByte())

/**
 * Convert a 0-254 byte value into a percentage value.
 *
 * This is Hue's native value system. It's unlikely you'd want to use this
 * unless you are working with other hue systems.
 */
val UByte.bytePercentage get() = Percentage(this)
