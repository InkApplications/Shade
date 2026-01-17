package inkapplications.shade.homekit

import inkapplications.shade.homekit.structures.Homekit

/**
 * Actions to get homekit resources in the hue system.
 */
interface HomekitControls {
    /**
     * Get a list of homekit resources configured on the hue service.
     */
    suspend fun listHomekit(): List<Homekit>
}
