package inkapplications.shade.cli.groupedlights

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object GetGroupedLightCommand: AuthorizedShadeCommand(
    help = "Get data for a specific grouped light"
) {
    private val id by argument().resourceId()

    override suspend fun runCommand(): Int {
        val group = shade.groupedLights.getGroup(id)

        logger.debug("Got Group: $group")
        echoGroup(group)

        return 0
    }
}
