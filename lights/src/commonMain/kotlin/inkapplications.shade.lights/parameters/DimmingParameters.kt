package inkapplications.shade.lights.parameters

import inkapplications.shade.serialization.WholePercentageSerializer
import inkapplications.spondee.scalar.Percentage
import kotlinx.serialization.Serializable

/**
 * Brightness changes for a light
 */
@Serializable
data class DimmingParameters(
    /**
     * Brightness percentage.
     *
     * Note: This cannot be zero. Specifying zero will use the lowest supported
     * brightness by the light.
     */
    @Serializable(with = WholePercentageSerializer::class)
    val brightness: Percentage,
)
