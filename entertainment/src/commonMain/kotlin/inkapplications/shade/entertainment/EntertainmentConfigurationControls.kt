package inkapplications.shade.entertainment

import inkapplications.shade.entertainment.structures.EntertainmentConfiguration
import inkapplications.shade.structures.ResourceId

/**
 * Actions to get entertainment configuration resources in the hue system.
 *
 * Entertainment configurations manage the setup for Hue Entertainment functionality,
 * including channel assignments, light positions, and streaming settings.
 */
interface EntertainmentConfigurationControls {
    /**
     * Get the state of a single entertainment configuration.
     *
     * @param id The resource ID of the entertainment configuration to fetch.
     */
    suspend fun getConfiguration(id: ResourceId): EntertainmentConfiguration

    /**
     * Get a list of entertainment configurations on the hue service.
     */
    suspend fun listConfigurations(): List<EntertainmentConfiguration>
}
