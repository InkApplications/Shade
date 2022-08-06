package inkapplications.shade.lights

import com.github.ajalt.colormath.Color
import com.github.ajalt.colormath.model.XYZ
import com.github.ajalt.colormath.model.xyY
import inkapplications.shade.serialization.DelegateSerializer
import kotlinx.serialization.Serializable

/**
 * X/Y chromaticity to represent a color
 */
@Serializable
internal data class Chromaticity(
    val x: Float,
    val y: Float,
) {
    val xyY: xyY get() = xyY(x, y)

    /**
     * Serialize an xyY color as an X-Y chromaticity value
     */
    internal object CieChromaticitySerializer: DelegateSerializer<Chromaticity, xyY>(serializer()) {
        override fun serialize(data: xyY): Chromaticity = Chromaticity(x = data.x, y = data.y)
        override fun deserialize(data: Chromaticity): xyY = xyY(x = data.x, y = data.y)
    }

    /**
     * Serialize a color as an X-Y chromaticity value
     */
    internal object ColorSerializer: DelegateSerializer<Chromaticity, Color>(serializer()) {
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
}
