package inkapplications.shade.lights.parameters

import inkapplications.shade.lights.structures.TimedLightEffect
import inkapplications.shade.lights.structures.Light
import inkapplications.shade.serialization.MillisecondDurationSerializer
import kotlinx.serialization.Serializable
import kotlin.time.Duration

/**
 * Basic feature containing timed effect properties.
 */
@Serializable
data class TimedEffectsParameters(
    /**
     * Effect to set the light to.
     *
     * Note: this should be an effect supported by the light. The list of
     * supported effects is in the [Light.timedEffects] property.
     */
    val effect: TimedLightEffect? = null,
    @Serializable(with = MillisecondDurationSerializer::class)
    val duration: Duration? = null,
)
