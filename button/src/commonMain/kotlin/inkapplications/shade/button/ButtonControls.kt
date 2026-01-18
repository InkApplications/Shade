package inkapplications.shade.button

import inkapplications.shade.button.structures.Button
import inkapplications.shade.structures.ResourceId

/**
 * Actions to manage devices with buttons.
 */
interface ButtonControls {
    /**
     * Get the state of a specified button resource.
     *
     * @param id The resource ID of the button resource to fetch data for.
     */
    suspend fun getButton(id: ResourceId): Button

    /**
     * Get a list of button resources configured on the hue service.
     */
    suspend fun listButtons(): List<Button>
}

