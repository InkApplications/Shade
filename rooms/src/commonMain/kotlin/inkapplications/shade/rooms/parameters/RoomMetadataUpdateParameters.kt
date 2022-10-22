package inkapplications.shade.rooms.parameters

import inkapplications.shade.structures.RoomArchetype
import kotlinx.serialization.Serializable

/**
 * Configuration data for a room available to specify on update.
 */
@Serializable
data class RoomMetadataUpdateParameters(
    /**
     * Configuration object for a room
     */
    val archetype: RoomArchetype? = null,

    /**
     * Human readable name of the room.
     */
    val name: String? = null,
)
