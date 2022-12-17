package inkapplications.shade.groupedlights.events

import inkapplications.shade.lights.structures.AlertInfo
import inkapplications.shade.lights.structures.DimmingInfo
import inkapplications.shade.structures.PowerInfo
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.UndocumentedApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data sent for a light group event.
 */
@Serializable
@UndocumentedApi
data class GroupedLightEvent(
    /**
     * The V2 unique identifier for the group.
     */
    val id: ResourceId,

    /**
     * Owner of the service
     *
     * In case the owner service is deleted, the service also gets deleted.
     */
    val owner: ResourceReference,

    /**
     * Information about the power-state of the light group.
     */
    @SerialName("on")
    val powerInfo: PowerInfo? = null,

    /**
     * Information about the group's dimming, if supported.
     */
    @SerialName("dimming")
    val dimmingInfo: DimmingInfo? = null,

    /**
     * Joined alert control for the light group.
     */
    @SerialName("alert")
    val alertInfo: AlertInfo? = null,
)
