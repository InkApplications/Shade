package inkapplications.shade.entertainment

import inkapplications.shade.entertainment.parameters.EntertainmentConfigurationCreateParameters
import inkapplications.shade.entertainment.structures.EntertainmentConfiguration
import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.internals.postData
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference

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

    override suspend fun createConfiguration(parameters: EntertainmentConfigurationCreateParameters): ResourceReference {
        val response: List<ResourceReference> = hueHttpClient.postData(
            body = parameters,
            pathSegments = arrayOf("resource", "entertainment_configuration"),
        )
        return response.single()
    }
}
