package inkapplications.shade.entertainment

import inkapplications.shade.entertainment.structures.Entertainment
import inkapplications.shade.structures.ResourceId

/**
 * Actions to get entertainment resources in the hue system.
 *
 * Entertainment resources represent the entertainment streaming capabilities
 * of lights and devices.
 */
interface EntertainmentControls {
    /**
     * Get the state of a single entertainment resource.
     *
     * @param id The resource ID of the entertainment resource to fetch data for.
     */
    suspend fun getEntertainment(id: ResourceId): Entertainment

    /**
     * Get a list of entertainment resources configured on the hue service.
     */
    suspend fun listEntertainment(): List<Entertainment>
}
