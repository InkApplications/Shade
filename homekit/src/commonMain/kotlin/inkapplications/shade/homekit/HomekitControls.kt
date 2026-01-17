package inkapplications.shade.homekit

import inkapplications.shade.homekit.structures.Homekit
import inkapplications.shade.structures.ResourceId

/**
 * Actions to get homekit resources in the hue system.
 */
interface HomekitControls {
    /**
     * Get the state of a single homekit resource.
     *
     * @param id The v2 resource ID of the homekit resource to fetch data for.
     */
    suspend fun getHomekit(id: ResourceId): Homekit

    /**
     * Get a list of homekit resources configured on the hue service.
     */
    suspend fun listHomekit(): List<Homekit>
}
