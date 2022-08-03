package inkapplications.shade.lights

import com.github.ajalt.colormath.Color
import com.github.ajalt.colormath.model.XYZ
import com.github.ajalt.colormath.model.xyY
import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Information about a light's color and color capabilities.
 */
@Serializable
data class ColorInfo(
    /**
     * Current color of the light
     */
    @SerialName("xy")
    @Serializable(with = ColorSerializer::class)
    val color: Color,

    /**
     * Simple Hue gamut type
     */
    @SerialName("gamut_type")
    val gamutType: GamutType,

    /**
     * Color gamut of color bulb. Some bulbs do not properly return the Gamut information. In this case this is not present.
     */
    @Serializable
    val gamut: Gamut? = null,
) {
    @Serializable
    internal data class Chromaticity(
        val x: Float,
        val y: Float,
    ) {
        val xyY: xyY get() = xyY(x, y)
    }

    internal object ColorSerializer: DelegateSerializer<Chromaticity, Color>(Chromaticity.serializer()) {
        override fun serialize(data: Color): Chromaticity {
            return Chromaticity(
                x = data.toXYZ().toCIExyY().x,
                y = data.toXYZ().toCIExyY().y,
            )
        }

        override fun deserialize(data: Chromaticity): Color {
            val chromaticity = xyY(data.x, data.y)

            return XYZ.invoke(chromaticity.X, chromaticity.Y, chromaticity.Z)
        }
    }

    internal object ChromaticitySerializer: DelegateSerializer<Chromaticity, xyY>(Chromaticity.serializer()) {
        override fun serialize(data: xyY): Chromaticity = Chromaticity(x = data.x, y = data.y)
        override fun deserialize(data: Chromaticity): xyY = xyY(x = data.x, y = data.y)
    }
}

