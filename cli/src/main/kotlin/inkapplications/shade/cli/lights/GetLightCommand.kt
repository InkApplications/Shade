package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.ShadeCommand
import inkapplications.shade.cli.resourceId

object GetLightCommand: ShadeCommand(
    help = "Get data for a specific light"
) {
    private val lightId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val light = shade.lights.getLight(lightId)

        debug { echo(light) }
        echoLight(light)

        return 0
    }
}
