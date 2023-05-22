package inkapplications.shade.resources

import inkapplications.shade.resources.structures.Resource

/**
 * Actions for API resources on the hue bridge
 */
interface ResourceControls {
    /**
     * Retrieve all known API resources
     */
    suspend fun listResources(): List<Resource>
}
