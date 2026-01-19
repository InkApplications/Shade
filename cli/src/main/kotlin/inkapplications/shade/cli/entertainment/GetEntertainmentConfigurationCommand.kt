package inkapplications.shade.cli.entertainment

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object GetEntertainmentConfigurationCommand: AuthorizedShadeCommand(
    help = "Get data for a specific entertainment configuration"
) {
    private val configurationId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val configuration = shade.entertainmentConfig.getConfiguration(configurationId)

        logger.debug("Got Entertainment Configuration: $configuration")
        echoEntertainmentConfiguration(configuration)

        return 0
    }
}
