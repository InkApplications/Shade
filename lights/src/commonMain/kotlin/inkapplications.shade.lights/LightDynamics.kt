package inkapplications.shade.lights

import inkapplications.shade.serialization.FractionalPercentageSerializer
import inkapplications.spondee.scalar.Percentage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Dynamic Lighting Effects information
 */
@Serializable
data class LightDynamics(
    /**
     * Current status of the lamp with dynamics.
     */
    val status: DynamicsStatus,

    /**
     * Statuses in which a lamp could be when playing dynamics.
     */
    @SerialName("status_values")
    val statusValues: List<DynamicsStatus>,

    /**
     * Speed of dynamic palette or effect.
     *
     * The speed is valid for the dynamic palette if the status is
     * [DynamicsStatus.DynamicPalette] or for the corresponding effect listed
     * in status.
     * In case of status [DynamicsStatus.None], the speed is not valid
     */
    @Serializable(with = FractionalPercentageSerializer::class)
    val speed: Percentage,

    /**
     * Indicates whether the value presented in speed is valid
     */
    @SerialName("speed_valid")
    val speedValid: Boolean,
)
