package inkapplications.shade.rooms.parameters

import inkapplications.shade.rooms.structures.RoomMetadata
import inkapplications.shade.rooms.structures.RoomMetadataUpdateParameters
import inkapplications.shade.structures.ResourceReference
import kotlinx.serialization.Serializable

/**
 * Parameters that can be specified when updating a room on the hue bridge.
 */
@Serializable
data class RoomUpdateParameters(
    /**
     * Configuration data for the room.
     */
    val metadata: RoomMetadataUpdateParameters? = null,

    /**
     * Devices to group by the Room
     */
    val children: List<ResourceReference>? = null,
)
