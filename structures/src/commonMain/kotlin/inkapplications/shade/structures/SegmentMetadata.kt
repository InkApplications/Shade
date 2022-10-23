package inkapplications.shade.structures

import kotlinx.serialization.Serializable

/**
 * Configuration data for a segment of devices, such as a room or zone.
 */
@Serializable
data class SegmentMetadata(
        /**
     * Category of type/purpose of the segment
     */
    val archetype: SegmentArchetype,

        /**
     * Human readable name of the room.
     */
    val name: String,
)
