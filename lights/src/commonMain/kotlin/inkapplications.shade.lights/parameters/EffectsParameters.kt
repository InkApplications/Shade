package inkapplications.shade.lights.parameters

import inkapplications.shade.lights.structures.LightEffect
import inkapplications.shade.lights.structures.Light
import kotlinx.serialization.Serializable

/**
 * Basic feature containing effect properties.
 */
@Serializable
data class EffectsParameters(
    /**
     * Effect to set the light to.
     *
     * Note: this should be an effect supported by the light. The list of
     * supported effects is in the [Light.effects] property.
     */
    val effect: LightEffect? = null,
)
