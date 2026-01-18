package inkapplications.shade.cli.homekit

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.enum
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId
import inkapplications.shade.homekit.parameters.HomekitUpdateParameters
import inkapplications.shade.homekit.structures.HomekitAction

object UpdateHomekitCommand: AuthorizedShadeCommand(
    help = "Invoke an action on a homekit resource"
) {
    private val homekitId by argument(
        help = "The ID of the homekit resource to be updated"
    ).resourceId()

    private val action by option(
        help = "Invoke an action on the homekit resource"
    ).enum<Actions>()

    override suspend fun runCommand(): Int {
        val action = when(action) {
            Actions.Reset -> HomekitAction.Reset
            else -> null
        }

        val response = shade.homekit.updateHomekit(
            id = homekitId,
            parameters = HomekitUpdateParameters(
                action = action,
            ),
        )

        logger.debug("Got response $response")

        return 0
    }

    private enum class Actions {
        Reset
    }
}
