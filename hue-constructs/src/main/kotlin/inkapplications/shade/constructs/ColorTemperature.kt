package inkapplications.shade.constructs

/**
 * Color temperature units.
 *
 * @param kelvinValue Color temperature in kelvin. Base unit to reduce rounding errors.
 */
data class ColorTemperature internal constructor(val kelvinValue: Int) {
    /**
     * Value in Mireds. Base unit of the Hue API.
     */
    val miredValue: Int = 1_000_000 / kelvinValue
}

/**
 * Express a number in the ColorTemperature unit of Kelvin.
 */
val Int.kelvin get() = ColorTemperature(this)

/**
 * Express a number in the ColorTemperature unit of Mireds.
 */
val Int.mireds get() = ColorTemperature(1_000_000 / this)

/**
 * Commonly used reference temperatures.
 *
 * @property A Incandescent / Tungsten
 * @property B Direct sunlight at noon
 * @property C Average / North sky Daylight
 * @property D50 Horizon Light. ICC profile PCS
 * @property D55 Mid-morning / Mid-afternoon Daylight
 * @property D65 Noon Daylight: Television, sRGB color space
 * @property D75 North sky Daylight
 * @property E Equal Energy
 * @property F1 Daylight Fluorescent
 * @property F2 Cool White Fluorescent
 * @property F3 White Fluorescent
 * @property F4 Warm White Fluorescent
 * @property F5 Daylight Fluorescent
 * @property F6 Lite White Fluorescent
 * @property F7 D65 simulator, Daylight simulator
 * @property F8 D50 simulator, Sylvania F40 Design 50
 * @property F9 Cool White Deluxe Fluorescent
 * @property F10 Philips TL85, Ultralume 50
 * @property F11 Philips TL84, Ultralume 40
 * @property F12 Philips TL83, Ultralume 30
 */
object StandardTemperatures {
    val A = 2856.kelvin
    val B = 4874.kelvin
    val C = 6774.kelvin
    val D50 = 5003.kelvin
    val D55 = 5503.kelvin
    val D65 = 6504.kelvin
    val D75 = 7504.kelvin
    val E = 5454.kelvin
    val F1 = 6430.kelvin
    val F2 = 4230.kelvin
    val F3 = 3450.kelvin
    val F4 = 2940.kelvin
    val F5 = 6350.kelvin
    val F6 = 4150.kelvin
    val F7 = 6500.kelvin
    val F8 = 5000.kelvin
    val F9 = 4150.kelvin
    val F10 = 5000.kelvin
    val F11 = 4000.kelvin
    val F12 = 3000.kelvin
}
