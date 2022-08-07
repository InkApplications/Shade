package inkapplications.shade.lights.parameters

import inkapplications.shade.serialization.FractionalPercentageSerializer
import inkapplications.shade.serialization.MillisecondDurationSerializer
import inkapplications.spondee.scalar.Percentage
import kotlinx.serialization.Serializable
import kotlin.time.Duration

/**
 * Settings for dynamics during a light setting change
 */
@Serializable
data class DynamicsParameters(
    /**
     * Duration of a light transition or timed effects in ms.
     */
    @Serializable(with = MillisecondDurationSerializer::class)
    val duration: Duration? = null,

    /**
     * speed of dynamic palette or effect.
     *
     * The speed is valid for the dynamic palette if the status is
     * `dynamic_palette` or for the corresponding effect listed in status.
     * In case of status `none`, the speed is not valid
     */
    @Serializable(with = FractionalPercentageSerializer::class)
    val speed: Percentage? = null,
)
