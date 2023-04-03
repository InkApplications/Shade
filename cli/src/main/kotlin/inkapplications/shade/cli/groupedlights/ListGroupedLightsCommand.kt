package inkapplications.shade.cli.groupedlights

import inkapplications.shade.cli.AuthorizedShadeCommand

object ListGroupedLightsCommand: AuthorizedShadeCommand(
    help = "Get all grouped lights configured on the Hue bridge",
) {
    override suspend fun runCommand(): Int {
        val groups = shade.groupedLights.listGroups()

        logger.debug("Got Groups: $groups")
        groups.forEach { echoGroup(it) }

        return 0
    }
}
