package inkapplications.shade.constructs

private const val MAX_VALUE = 254

/**
 * Unit of brightness.
 *
 * @param byteValue an unsigned byte value, unit for the Hue API.
 */
data class Brightness internal constructor(val byteValue: UByte): Comparable<Brightness> {
    val percentageValue: Float = byteValue.toFloat() / MAX_VALUE.toFloat()

    override fun compareTo(other: Brightness): Int = byteValue.compareTo(other.byteValue)
}

/**
 * Express brightness with a fractional percentage.
 */
val Double.percentageBrightness get() = Brightness((MAX_VALUE.toDouble() * this).toInt().toUByte())

/**
 * Express brightness with a fractional percentage.
 */
val Float.percentageBrightness get() = Brightness((MAX_VALUE.toFloat() * this).toInt().toUByte())

/**
 * Convert a 0-254 byte value into a brightness value.
 */
val Int.asByteBrightness get() = Brightness(toUByte())

/**
 * Convert an unsigned byte value into a brightness value.
 */
val UByte.asBrightness get() = Brightness(this)
