package inkapplications.shade.cli.lights

import inkapplications.shade.cli.ShadeCommand

object ListLightsCommand: ShadeCommand(
    help = "Get all lights connected to the bridge"
) {
    override suspend fun runCommand(): Int {
        val lights = shade.lights.getLights()

        logger.debug("Got Lights: $lights")
        lights.forEach { echoLight(it) }

        return 0
    }
}
