package inkapplications.shade.entertainment

import inkapplications.shade.entertainment.structures.EntertainmentConfiguration

/**
 * Actions to get entertainment configuration resources in the hue system.
 *
 * Entertainment configurations manage the setup for Hue Entertainment functionality,
 * including channel assignments, light positions, and streaming settings.
 */
interface EntertainmentConfigurationControls {
    /**
     * Get a list of entertainment configurations on the hue service.
     */
    suspend fun listConfigurations(): List<EntertainmentConfiguration>
}
