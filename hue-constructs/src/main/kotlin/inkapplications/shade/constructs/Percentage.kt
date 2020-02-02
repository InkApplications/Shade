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
 */
val Double.percentage get() = Percentage((MAX_VALUE.toDouble() * this).toInt().toUByte())

/**
 * Express with a fractional percentage.
 */
val Float.percentage get() = Percentage((MAX_VALUE.toFloat() * this).toInt().toUByte())

/**
 * Convert a 0-254 byte value into a percentage value.
 */
val Int.bytePercentage get() = Percentage(toUByte())

/**
 * Convert an unsigned byte value into a percentage value.
 */
val UByte.percentage get() = Percentage(this)
