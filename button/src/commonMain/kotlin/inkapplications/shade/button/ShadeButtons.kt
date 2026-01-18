package inkapplications.shade.button

import inkapplications.shade.button.structures.Button
import inkapplications.shade.internals.HueHttpClient
import inkapplications.shade.internals.getData
import inkapplications.shade.structures.ResourceId

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
}

