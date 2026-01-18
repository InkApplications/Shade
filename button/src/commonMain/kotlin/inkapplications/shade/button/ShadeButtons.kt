package inkapplications.shade.button

 import inkapplications.shade.button.parameters.ButtonUpdateParameters
import inkapplications.shade.button.structures.Button
import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.internals.putData
import inkapplications.shade.structures.ResourceId
import inkapplications.shade.structures.ResourceReference

/**
 * Implements button controls via the hue client.
 */
internal class ShadeButtons(
    private val hueHttpClient: HueHttpClient,
) : ButtonControls {
    override suspend fun getButton(id: ResourceId): Button {
        return hueHttpClient.getData<List<Button>>("resource", "button", id.value).single()
    }

    override suspend fun listButtons(): List<Button> {
        return hueHttpClient.getData("resource", "button")
    }

    override suspend fun updateButton(
        id: ResourceId,
        parameters: ButtonUpdateParameters
    ): ResourceReference {
        val response: List<ResourceReference> = hueHttpClient.putData(
            body = parameters,
            pathSegments = arrayOf("resource", "button", id.value),
        )

        return response.single()
    }
}
