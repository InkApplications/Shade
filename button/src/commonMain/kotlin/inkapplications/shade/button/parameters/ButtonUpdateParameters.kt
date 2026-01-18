package inkapplications.shade.button.parameters

import inkapplications.shade.structures.ResourceType
import kotlinx.serialization.Serializable

/**
 * Parameters for updating a button resource.
 */
@Serializable
data class ButtonUpdateParameters(
    /**
     * Type of the supported resources.
     */
    val type: ResourceType? = null,
)

