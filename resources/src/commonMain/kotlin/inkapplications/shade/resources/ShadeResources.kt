package inkapplications.shade.resources

import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.resources.structures.Resource

/**
 * Implements Resource Controls using the hue client.
 */
internal class ShadeResources(
    private val hueHttpClient: HueHttpClient,
): ResourceControls {
    override suspend fun listResources(): List<Resource> {
        return hueHttpClient.getData("resource")
    }
}
