package inkapplications.shade.cli.entertainment

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object DeleteEntertainmentConfigurationCommand: AuthorizedShadeCommand(
    help = "Delete an entertainment configuration from the Hue bridge"
) {
    private val configurationId by argument(
        help = "The ID of the entertainment configuration to delete"
    ).resourceId()

    override suspend fun runCommand(): Int {
        val response = shade.entertainmentConfig.deleteConfiguration(configurationId)

        logger.debug("Got response: $response")
        echo("Deleted entertainment configuration: ${response.id}")

        return 0
    }
}
