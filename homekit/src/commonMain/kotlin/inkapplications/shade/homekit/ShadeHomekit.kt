package inkapplications.shade.homekit

import inkapplications.shade.homekit.parameters.HomekitUpdateParameters
import inkapplications.shade.homekit.structures.Homekit
import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.internals.putData
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference

/**
 * Implements homekit controls via the hue client.
 */
internal class ShadeHomekit(
    private val hueHttpClient: HueHttpClient,
) : HomekitControls {
    override suspend fun getHomekit(id: ResourceId): Homekit {
        return hueHttpClient.getData<List<Homekit>>("resource", "homekit", id.value).single()
    }

    override suspend fun listHomekit(): List<Homekit> {
        return hueHttpClient.getData("resource", "homekit")
    }

    override suspend fun updateHomekit(
        id: ResourceId,
        parameters: HomekitUpdateParameters
    ): ResourceReference {
        val response: List<ResourceReference> = hueHttpClient.putData(
            body = parameters,
            pathSegments = arrayOf("resource", "homekit", id.value),
        )

        return response.single()
    }
}
