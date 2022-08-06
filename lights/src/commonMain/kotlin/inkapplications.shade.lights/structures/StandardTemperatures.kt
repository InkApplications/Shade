package inkapplications.shade.lights.structures

import inkapplications.spondee.measure.Kelvin
import inkapplications.spondee.measure.toColorTemperature

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
    val A = Kelvin.of(2856).toColorTemperature()
    val B = Kelvin.of(4874).toColorTemperature()
    val C = Kelvin.of(6774).toColorTemperature()
    val D50 = Kelvin.of(5003).toColorTemperature()
    val D55 = Kelvin.of(5503).toColorTemperature()
    val D65 = Kelvin.of(6504).toColorTemperature()
    val D75 = Kelvin.of(7504).toColorTemperature()
    val E = Kelvin.of(5454).toColorTemperature()
    val F1 = Kelvin.of(6430).toColorTemperature()
    val F2 = Kelvin.of(4230).toColorTemperature()
    val F3 = Kelvin.of(3450).toColorTemperature()
    val F4 = Kelvin.of(2940).toColorTemperature()
    val F5 = Kelvin.of(6350).toColorTemperature()
    val F6 = Kelvin.of(4150).toColorTemperature()
    val F7 = Kelvin.of(6500).toColorTemperature()
    val F8 = Kelvin.of(5000).toColorTemperature()
    val F9 = Kelvin.of(4150).toColorTemperature()
    val F10 = Kelvin.of(5000).toColorTemperature()
    val F11 = Kelvin.of(4000).toColorTemperature()
    val F12 = Kelvin.of(3000).toColorTemperature()
}
