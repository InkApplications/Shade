package inkapplications.shade.cli.zones

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object GetZoneCommand: AuthorizedShadeCommand(
    help = "Get information for a specific Zone"
) {
    private val id by argument().resourceId()

    override suspend fun runCommand(): Int {
        val zone = shade.zones.getZone(id)

        echoZone(zone)

        return 0
    }
}
