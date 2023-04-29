package inkapplications.shade.lights.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains information on a single gradient point.
 */
@Serializable
data class GradientPoint(
    @SerialName("color")
    val colorValue: ColorValue,
) {
    @Deprecated("Use ColorValue when constructing a gradient")
    constructor(
        colorInfo: GradientColorInfo,
    ): this(ColorValue(colorInfo.color))
    /**
     * Color info for this point.
     */
    @Deprecated("Use colorValue", ReplaceWith("colorValue"))
    val colorInfo: GradientColorInfo = GradientColorInfo(colorValue.color)
}
