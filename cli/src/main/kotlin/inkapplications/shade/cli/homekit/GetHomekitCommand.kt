package inkapplications.shade.cli.homekit

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object GetHomekitCommand: AuthorizedShadeCommand(
    help = "Get data for a specific homekit resource"
) {
    private val homekitId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val homekit = shade.homekit.getHomekit(homekitId)

        logger.debug("Got Homekit: $homekit")
        echoHomekit(homekit)

        return 0
    }
}

