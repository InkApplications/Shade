package inkapplications.shade.cli.homekit

import inkapplications.shade.cli.AuthorizedShadeCommand

object ListHomekitCommand: AuthorizedShadeCommand(
    help = "Get all of the homekit resources configured on the Hue bridge"
) {
    override suspend fun runCommand(): Int {
        val homekitResources = shade.homekit.listHomekit()

        logger.debug("Got Homekit: $homekitResources")
        homekitResources.forEach(::echoHomekit)

        return 0
    }
}

