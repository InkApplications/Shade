package inkapplications.shade.cli.lightlevels

import inkapplications.shade.cli.AuthorizedShadeCommand

object ListLightLevelsCommand: AuthorizedShadeCommand(
    help = "Get all of the light level sensors configured on the Hue bridge"
) {
    override suspend fun runCommand(): Int {
        val lightLevels = shade.lightLevels.listLightLevels()

        logger.debug("Got Light Levels: $lightLevels")
        lightLevels.forEach(::echoLightLevel)

        return 0
    }
}
