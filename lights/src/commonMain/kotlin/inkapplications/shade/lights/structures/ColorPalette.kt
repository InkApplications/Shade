package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable

/**
 * Color/brightness pair reference.
 */
@Serializable
data class ColorPalette(
    val color: ColorValue,
    val dimming: DimmingValue,
)
