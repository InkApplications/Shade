package inkapplications.shade.zones

import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.zones.structures.Zone

/**
 * Implements zone controls via the hue client.
 */
internal class ShadeZones(
    private val hueHttpClient: HueHttpClient,
): ZoneControls {
    override suspend fun listZones(): List<Zone> {
        return hueHttpClient.getData("resource", "zone")
    }
}
