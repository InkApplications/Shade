package inkapplications.shade.cli.zones

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object DeleteZoneCommand: AuthorizedShadeCommand(
    help = "Delete a zone from the hue bridge"
) {
    private val zoneId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val response = shade.zones.deleteZone(zoneId)

        logger.debug("Got response: $response")

        return 0
    }
}
