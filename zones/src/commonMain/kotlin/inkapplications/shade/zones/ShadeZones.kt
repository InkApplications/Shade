package inkapplications.shade.zones

import inkapplications.shade.internals.*
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.zones.parameters.ZoneCreateParameters
import inkapplications.shade.zones.parameters.ZoneUpdateParameters
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

    override suspend fun getZone(id: ResourceId): Zone {
        return hueHttpClient.getData<List<Zone>>("resource", "zone", id.value).single()
    }

    override suspend fun updateZone(id: ResourceId, parameters: ZoneUpdateParameters): ResourceReference {
        val response: List<ResourceReference> = hueHttpClient.putData(
            body = parameters,
            pathSegments = arrayOf("resource", "zone", id.value),
        )

        return response.single()
    }

    override suspend fun createZone(parameters: ZoneCreateParameters): ResourceReference {
        val response: List<ResourceReference> = hueHttpClient.postData(
            body = parameters,
            pathSegments = arrayOf("resource", "zone"),
        )

        return response.single()
    }

    override suspend fun deleteZone(id: ResourceId): ResourceReference {
        return hueHttpClient.deleteData<List<ResourceReference>>("resource", "zone", id.value).single()
    }
}
