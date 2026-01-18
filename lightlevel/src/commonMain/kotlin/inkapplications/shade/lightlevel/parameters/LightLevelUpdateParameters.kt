package inkapplications.shade.lightlevel.parameters

import kotlinx.serialization.Serializable

/**
 * Parameters that can be specified when updating a light level sensor.
 */
@Serializable
data class LightLevelUpdateParameters(
    /**
     * Whether the sensor is activated.
     *
     * true when sensor is activated, false when deactivated.
     */
    val enabled: Boolean? = null,
)

