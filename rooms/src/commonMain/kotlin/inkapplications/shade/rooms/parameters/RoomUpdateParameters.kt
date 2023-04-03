package inkapplications.shade.rooms.parameters

import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.SegmentMetadataUpdate
import kotlinx.serialization.Serializable

/**
 * Parameters that can be specified when updating a room on the hue bridge.
 */
@Serializable
data class RoomUpdateParameters(
    /**
     * Configuration data for the room.
     */
    val metadata: SegmentMetadataUpdate? = null,

    /**
     * Devices to group by the Room
     */
    val children: List<ResourceReference>? = null,
)
