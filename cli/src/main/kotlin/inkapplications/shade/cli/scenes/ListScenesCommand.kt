package inkapplications.shade.cli.scenes

import inkapplications.shade.cli.AuthorizedShadeCommand

object ListScenesCommand: AuthorizedShadeCommand(
    help = "Get all of the scenes configured on the Hue bridge",
) {
    override suspend fun runCommand(): Int {
        val scenes = shade.scenes.listScenes()

        logger.debug("Got Scenes: $scenes")
        scenes.forEach(::echoScene)

        return 0
    }
}
