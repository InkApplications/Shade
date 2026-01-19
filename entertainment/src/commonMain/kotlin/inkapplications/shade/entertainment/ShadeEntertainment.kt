package inkapplications.shade.entertainment

import inkapplications.shade.entertainment.structures.Entertainment
import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.structures.ResourceId

/**
 * Implements entertainment controls via the hue client.
 */
internal class ShadeEntertainment(
    private val hueHttpClient: HueHttpClient,
) : EntertainmentControls {
    override suspend fun getEntertainment(id: ResourceId): Entertainment {
        return hueHttpClient.getData<List<Entertainment>>("resource", "entertainment", id.value).single()
    }

    override suspend fun listEntertainment(): List<Entertainment> {
        return hueHttpClient.getData("resource", "entertainment")
    }
}
