package inkapplications.shade.entertainment

import inkapplications.shade.entertainment.structures.EntertainmentConfiguration
import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.structures.ResourceId

/**
 * Implements entertainment configuration controls via the hue client.
 */
internal class ShadeEntertainmentConfigurations(
    private val hueHttpClient: HueHttpClient,
) : EntertainmentConfigurationControls {
    override suspend fun getConfiguration(id: ResourceId): EntertainmentConfiguration {
        return hueHttpClient.getData<List<EntertainmentConfiguration>>("resource", "entertainment_configuration", id.value).single()
    }

    override suspend fun listConfigurations(): List<EntertainmentConfiguration> {
        return hueHttpClient.getData("resource", "entertainment_configuration")
    }
}
