package inkapplications.shade.rooms.parameters

import inkapplications.shade.rooms.structures.RoomMetadata
import inkapplications.shade.structures.ResourceReference
import kotlinx.serialization.Serializable

/**
 * Data used for creating a new room type via the bridge.
 */
@Serializable
data class RoomCreateParameters(
    /**
     * Configuration data for the room.
     */
    val metadata: RoomMetadata,

    /**
     * Devices to group by the Room
     */
    val children: List<ResourceReference>,
)
