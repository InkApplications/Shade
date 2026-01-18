package inkapplications.shade.cli.buttons

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.button.parameters.ButtonUpdateParameters
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId
import inkapplications.shade.structures.ResourceType

object UpdateButtonCommand: AuthorizedShadeCommand(
    help = "Update an existing button on the Hue bridge"
) {
    private val buttonId by argument(
        help = "The ID of the button resource to be updated"
    ).resourceId()

    override suspend fun runCommand(): Int {
        val response = shade.buttons.updateButton(
            id = buttonId,
            parameters = ButtonUpdateParameters(
                type = ResourceType.Button,
            ),
        )

        logger.debug("Got response $response")

        return 0
    }
}

