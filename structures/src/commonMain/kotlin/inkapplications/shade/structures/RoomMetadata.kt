package inkapplications.shade.structures

import kotlinx.serialization.Serializable

/**
 * Configuration data for a room.
 */
@Serializable
data class RoomMetadata(
    /**
     * Configuration object for a room
     */
    val archetype: RoomArchetype,

    /**
     * Human readable name of the room.
     */
    val name: String,
)
