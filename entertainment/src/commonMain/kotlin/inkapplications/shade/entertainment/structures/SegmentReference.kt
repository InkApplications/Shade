package inkapplications.shade.entertainment.structures

import inkapplications.shade.structures.ResourceReference
import kotlinx.serialization.Serializable

/**
 * Reference to a segment that is a member of an entertainment channel.
 */
@Serializable
data class SegmentReference(
    /**
     * Reference to the entertainment service containing the segment.
     */
    val service: ResourceReference,

    /**
     * Index of the segment within the entertainment service.
     */
    val index: Int,
)
