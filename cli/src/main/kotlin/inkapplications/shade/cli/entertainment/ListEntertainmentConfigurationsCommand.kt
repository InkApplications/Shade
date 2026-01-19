package inkapplications.shade.cli.entertainment

import inkapplications.shade.cli.AuthorizedShadeCommand

object ListEntertainmentConfigurationsCommand: AuthorizedShadeCommand(
    help = "Get all of the entertainment configurations on the Hue bridge"
) {
    override suspend fun runCommand(): Int {
        val configurations = shade.entertainmentConfig.listConfigurations()

        logger.debug("Got Entertainment Configurations: $configurations")
        configurations.forEach(::echoEntertainmentConfiguration)

        return 0
    }
}
