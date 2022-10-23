package inkapplications.shade.zones.parameters

import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.SegmentMetadata
import kotlinx.serialization.Serializable

/**
 * Parameters used for creating a new Zone on the Hue bridge
 */
@Serializable
data class ZoneCreateParameters(
    /**
     * Configuration metadata for the Zone.
     */
    val metadata: SegmentMetadata,

    /**
     * Child resources to group by the Zone.
     */
    val children: List<ResourceReference>,
)
