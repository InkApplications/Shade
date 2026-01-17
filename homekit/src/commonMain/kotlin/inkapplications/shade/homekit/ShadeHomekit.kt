package inkapplications.shade.homekit

import inkapplications.shade.homekit.structures.Homekit
import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.structures.ResourceId

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
}
