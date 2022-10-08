package inkapplications.shade.lights.structures

import inkapplications.shade.lights.structures.StandardTemperatures.A
import inkapplications.shade.lights.structures.StandardTemperatures.B
import inkapplications.shade.lights.structures.StandardTemperatures.C
import inkapplications.shade.lights.structures.StandardTemperatures.D50
import inkapplications.shade.lights.structures.StandardTemperatures.D55
import inkapplications.shade.lights.structures.StandardTemperatures.D65
import inkapplications.shade.lights.structures.StandardTemperatures.D75
import inkapplications.shade.lights.structures.StandardTemperatures.E
import inkapplications.shade.lights.structures.StandardTemperatures.F1
import inkapplications.shade.lights.structures.StandardTemperatures.F10
import inkapplications.shade.lights.structures.StandardTemperatures.F11
import inkapplications.shade.lights.structures.StandardTemperatures.F12
import inkapplications.shade.lights.structures.StandardTemperatures.F2
import inkapplications.shade.lights.structures.StandardTemperatures.F3
import inkapplications.shade.lights.structures.StandardTemperatures.F4
import inkapplications.shade.lights.structures.StandardTemperatures.F5
import inkapplications.shade.lights.structures.StandardTemperatures.F6
import inkapplications.shade.lights.structures.StandardTemperatures.F7
import inkapplications.shade.lights.structures.StandardTemperatures.F8
import inkapplications.shade.lights.structures.StandardTemperatures.F9
import inkapplications.spondee.measure.ColorTemperature
import inkapplications.spondee.measure.metric.kelvin

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
    val A: ColorTemperature = 2856.kelvin
    val B: ColorTemperature = 4874.kelvin
    val C: ColorTemperature = 6774.kelvin
    val D50: ColorTemperature = 5003.kelvin
    val D55: ColorTemperature = 5503.kelvin
    val D65: ColorTemperature = 6504.kelvin
    val D75: ColorTemperature = 7504.kelvin
    val E: ColorTemperature = 5454.kelvin
    val F1: ColorTemperature = 6430.kelvin
    val F2: ColorTemperature = 4230.kelvin
    val F3: ColorTemperature = 3450.kelvin
    val F4: ColorTemperature = 2940.kelvin
    val F5: ColorTemperature = 6350.kelvin
    val F6: ColorTemperature = 4150.kelvin
    val F7: ColorTemperature = 6500.kelvin
    val F8: ColorTemperature = 5000.kelvin
    val F9: ColorTemperature = 4150.kelvin
    val F10: ColorTemperature = 5000.kelvin
    val F11: ColorTemperature = 4000.kelvin
    val F12: ColorTemperature = 3000.kelvin
}
