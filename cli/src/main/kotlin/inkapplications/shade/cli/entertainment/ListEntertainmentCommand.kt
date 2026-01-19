package inkapplications.shade.cli.entertainment

import inkapplications.shade.cli.AuthorizedShadeCommand

object ListEntertainmentCommand: AuthorizedShadeCommand(
    help = "Get all of the entertainment resources configured on the Hue bridge"
) {
    override suspend fun runCommand(): Int {
        val entertainmentResources = shade.entertainment.listEntertainment()

        logger.debug("Got Entertainment: $entertainmentResources")
        entertainmentResources.forEach(::echoEntertainment)

        return 0
    }
}

