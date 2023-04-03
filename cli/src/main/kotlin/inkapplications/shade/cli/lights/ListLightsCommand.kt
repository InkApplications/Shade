package inkapplications.shade.cli.lights

import inkapplications.shade.cli.AuthorizedShadeCommand

object ListLightsCommand: AuthorizedShadeCommand(
    help = "Get all lights connected to the bridge"
) {
    override suspend fun runCommand(): Int {
        val lights = shade.lights.listLights()

        logger.debug("Got Lights: $lights")
        lights.forEach { echoLight(it) }

        return 0
    }
}
