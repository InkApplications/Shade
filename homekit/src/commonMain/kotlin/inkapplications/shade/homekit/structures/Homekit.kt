package inkapplications.shade.homekit.structures

import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceType
import kotlinx.serialization.Serializable

/**
 * State of the homekit resource.
 */
@Serializable
data class Homekit(
    /**
     * Unique identifier representing a specific homekit resource instance.
     */
    val id: ResourceId,

    /**
     * Type of the supported resources.
     */
    val type: ResourceType = ResourceType.Homekit,

    /**
     * Status indicating whether homekit is paired, currently open for pairing, or unpaired.
     */
    val status: HomekitStatus,
)

