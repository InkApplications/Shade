package inkapplications.shade.lightlevel.structures

import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.ResourceType
import kotlinx.serialization.Serializable

/**
 * State and capabilities of a light level sensor resource.
 */
@Serializable
data class LightLevel(
    /**
     * Unique identifier representing a specific light level sensor instance.
     */
    val id: ResourceId,

    /**
     * Owner of the service.
     *
     * In case the owner service is deleted, the service also gets deleted.
     */
    val owner: ResourceReference,

    /**
     * Whether the sensor is activated.
     *
     * true when sensor is activated, false when deactivated.
     */
    val enabled: Boolean,

    /**
     * Light level sensor data.
     */
    val light: LightLevelValue,

    /**
     * Type of the supported resource.
     */
    val type: ResourceType = ResourceType.LightLevel,
)
