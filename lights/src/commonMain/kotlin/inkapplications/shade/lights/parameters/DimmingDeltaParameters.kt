package inkapplications.shade.lights.parameters

import inkapplications.shade.serialization.WholePercentageSerializer
import inkapplications.spondee.scalar.Percentage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Relative brightness changes for a light.
 */
@Serializable
data class DimmingDeltaParameters(
    /**
     * Type of delta being defined
     */
    val action: DeltaAction,

    /**
     * Percentage brightness to be added to the light.
     *
     * Note that this percentage is relative addition, not multiplicative.
     * e.g. Specifying 10% here for light at 50% brightness will result in
     * the light being 60% brightness.
     */
    @Serializable(with = WholePercentageSerializer::class)
    @SerialName("brightness_delta")
    val brightnessDelta: Percentage? = null,
)
