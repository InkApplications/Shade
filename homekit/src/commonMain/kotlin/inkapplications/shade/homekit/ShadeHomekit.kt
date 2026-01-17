package inkapplications.shade.homekit

import inkapplications.shade.homekit.structures.Homekit
import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData

/**
 * Implements homekit controls via the hue client.
 */
internal class ShadeHomekit(
    private val hueHttpClient: HueHttpClient,
) : HomekitControls {
    override suspend fun listHomekit(): List<Homekit> {
        return hueHttpClient.getData("resource", "homekit")
    }
}
