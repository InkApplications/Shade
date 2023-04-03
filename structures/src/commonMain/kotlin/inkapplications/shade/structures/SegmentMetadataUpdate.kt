package inkapplications.shade.structures

import kotlinx.serialization.Serializable

/**
 * Configuration data for a segment of devices, such as a room or zone.@author
 *
 * This represents the modifyable options for [SegmentMetadata] as optional
 * properties.
 */
@Serializable
data class SegmentMetadataUpdate(
    /**
     * Category of type/purpose of the segment
     */
    val archetype: SegmentArchetype? = null,

    /**
     * Human readable name of the room.
     */
    val name: String? = null,
)
