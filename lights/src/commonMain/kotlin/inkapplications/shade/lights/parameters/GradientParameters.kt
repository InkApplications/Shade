package inkapplications.shade.lights.parameters

import inkapplications.shade.lights.structures.GradientPoint
import kotlinx.serialization.Serializable

/**
 * Basic feature containing gradient properties.
 */
@Serializable
data class GradientParameters(
    /**
     * Collection of gradients points.
     *
     * Note: minimum of 2 points need to be provided.
     */
    val points: List<GradientPoint>,
) {
    init {
        if (points.size < 2) throw IllegalArgumentException("Gradient must contain at least two points")
        if (points.size > 5) throw IllegalArgumentException("Gradient cannot contain more than 5 points")
    }
}
