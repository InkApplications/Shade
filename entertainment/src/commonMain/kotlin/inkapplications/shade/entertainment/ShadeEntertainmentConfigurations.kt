package inkapplications.shade.entertainment

import inkapplications.shade.entertainment.structures.EntertainmentConfiguration
import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData

/**
 * Implements entertainment configuration controls via the hue client.
 */
internal class ShadeEntertainmentConfigurations(
    private val hueHttpClient: HueHttpClient,
) : EntertainmentConfigurationControls {
    override suspend fun listConfigurations(): List<EntertainmentConfiguration> {
        return hueHttpClient.getData("resource", "entertainment_configuration")
    }
}
