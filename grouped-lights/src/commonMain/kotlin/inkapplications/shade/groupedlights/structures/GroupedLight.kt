package inkapplications.shade.groupedlights.structures

import inkapplications.shade.lights.structures.AlertInfo
import inkapplications.shade.lights.structures.DimmingInfo
import inkapplications.shade.structures.PowerInfo
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A configured light group for a room, zone or home.
 */
@Serializable
data class GroupedLight(
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
    val dimmingInfo: GroupDimmingInfo? = null,

    /**
     * Joined alert control for the light group.
     */
    @SerialName("alert")
    val alertInfo: AlertInfo? = null,
)
