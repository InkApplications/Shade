package inkapplications.shade.lights

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Basic feature containing gradient properties.
 */
@Serializable
data class Gradient(
    /**
     * Collection of gradients points.
     */
    val points: List<GradientPoint>,

    /**
     * Number of color points that gradient lamp is capable of showing with gradience.
     */
    @SerialName("points_capable")
    val pointsCapable: Int,
) {
    init {
        if (points.size > 5) throw IllegalArgumentException("Gradient cannot contain more than 5 points")
    }
}
