package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Mode in which gradient points are currently being deployed.
 */
@JvmInline
@Serializable
value class GradientMode(val key: String) {
    companion object {
        val InterpolatedPalette = GradientMode("interpolated_palette")
        val InterpolatedPaletteMirrored = GradientMode("interpolated_palette_mirrored")
        val RandomPixelated = GradientMode("random_pixelated")
    }
}
