package inkapplications.shade.lights

import com.github.ajalt.colormath.Illuminant
import com.github.ajalt.colormath.model.RGBColorSpace
import com.github.ajalt.colormath.model.xyY
import kotlinx.serialization.Serializable

/**
 * RGB Chromaticity values defining a color space gamut
 */
@Serializable
data class Gamut(
    @Serializable(with = Chromaticity.CieChromaticitySerializer::class)
    val red: xyY,
    @Serializable(with = Chromaticity.CieChromaticitySerializer::class)
    val green: xyY,
    @Serializable(with = Chromaticity.CieChromaticitySerializer::class)
    val blue: xyY,
) {
    fun toColorSpace() = RGBColorSpace(
        name = "Hue Specified Colorspace",
        whitePoint = Illuminant.D65,
        transferFunctions = RGBColorSpace.LinearTransferFunctions,
        r = red,
        g = green,
        b = blue,
    )
}
