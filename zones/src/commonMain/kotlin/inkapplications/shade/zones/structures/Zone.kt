package inkapplications.shade.zones.structures

import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.RoomArchetype
import inkapplications.shade.structures.RoomMetadata
import kotlinx.serialization.Serializable

/**
 * State and capabilities of a zone resource.
 */
@Serializable
data class Zone(
    /**
     * Unique v2 ID for the zone.
     */
    val id: ResourceId,

    /**
     * References all services aggregating control and state of children
     * in the group.
     *
     * This includes all services grouped in the group hierarchy given by
     * child relation This includes all services of a device grouped in the
     * group hierarchy given by child relation Aggregation is per service type,
     * ie. every service type which can be grouped has a corresponding
     * definition of grouped type Supported types "light"
     */
    val services: List<ResourceReference>,

    /**
     * Configuration metatdata for the room/zone type.
     */
    val metadata: RoomMetadata,

    /**
     * Children devices of this zone.
     */
    val children: List<ResourceReference>,
)
