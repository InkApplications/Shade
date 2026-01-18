package inkapplications.shade.cli.lightlevels

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object GetLightLevelCommand: AuthorizedShadeCommand(
    help = "Get data for a specific light level sensor"
) {
    private val lightLevelId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val lightLevel = shade.lightLevels.getLightLevel(lightLevelId)

        logger.debug("Got Light Level: $lightLevel")
        echoLightLevel(lightLevel)

        return 0
    }
}
