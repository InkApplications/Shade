package inkapplications.shade.homekit.parameters

import inkapplications.shade.homekit.structures.HomekitAction
import kotlinx.serialization.Serializable

/**
 * Options for updating a homekit resource.
 */
@Serializable
data class HomekitUpdateParameters(
    /**
     * Action to perform on the homekit resource.
     */
    val action: HomekitAction? = null,
)

