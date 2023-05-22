package inkapplications.shade.lights.structures

import kotlinx.serialization.Serializable

/**
 * Basic feature containing gradient properties.
 */
@Serializable
data class GradientValue(
    /**
     * Collection of gradients points.
     */
    val points: List<GradientPoint>,

    /**
     * Mode in which the points are currently being deployed.
     */
    val mode: GradientMode,
) {
    init {
        if (points.size > 5) throw IllegalArgumentException("Gradient cannot contain more than 5 points")
    }
}
