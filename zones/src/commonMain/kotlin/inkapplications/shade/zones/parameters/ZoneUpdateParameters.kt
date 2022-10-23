package inkapplications.shade.zones.parameters

import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.SegmentMetadataUpdate
import kotlinx.serialization.Serializable

/**
 * Parameters for updating a Zone's information.
 */
@Serializable
data class ZoneUpdateParameters(
    /**
     * Metadata configuration for the zone.
     */
    val metadata: SegmentMetadataUpdate? = null,

    /**
     * Child services to group by the Zone.
     */
    val children: List<ResourceReference>? = null,
)
