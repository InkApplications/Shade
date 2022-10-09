package inkapplications.shade.rooms.structures

import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import kotlinx.serialization.Serializable

/**
 * State and capabilities of a room resource.
 */
@Serializable
data class Room(
    /**
     * Unique identifier representing a specific room instance.
     */
    val id: ResourceId,

    /**
     * References all services aggregating control and state of children
     * in the group.
     *
     * This includes all services grouped in the group hierarchy given by child
     * relation This includes all services of a device grouped in the group
     * hierarchy given by child relation Aggregation is per service type,
     * ie every service type which can be grouped has a corresponding definition
     * of grouped type Supported types “light”
     */
    val services: List<ResourceReference>,

    /**
     * Configuration object for a room.
     */
    val metadata: RoomMetadata,

    /**
     * Devices to group by the Room.
     */
    val children: List<ResourceReference>,
)
