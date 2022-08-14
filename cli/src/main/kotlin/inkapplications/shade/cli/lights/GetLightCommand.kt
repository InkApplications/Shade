package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object GetLightCommand: AuthorizedShadeCommand(
    help = "Get data for a specific light"
) {
    private val lightId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val light = shade.lights.getLight(lightId)

        logger.debug("Got Light: $light")
        echoLight(light)

        return 0
    }
}
