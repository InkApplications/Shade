package inkapplications.shade.homekit

import inkapplications.shade.homekit.parameters.HomekitUpdateParameters
import inkapplications.shade.homekit.structures.Homekit
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference

/**
 * Actions to get homekit resources in the hue system.
 */
interface HomekitControls {
    /**
     * Get the state of a single homekit resource.
     *
     * @param id The resource ID of the homekit resource to fetch data for.
     */
    suspend fun getHomekit(id: ResourceId): Homekit

    /**
     * Get a list of homekit resources configured on the hue service.
     */
    suspend fun listHomekit(): List<Homekit>

    /**
     * Invoke an action on the specified homekit resource.
     *
     * @param id The resource ID of the homekit resource to be updated
     * @param parameters Data about the homekit resource to be updated
     */
    suspend fun updateHomekit(id: ResourceId, parameters: HomekitUpdateParameters): ResourceReference
}
