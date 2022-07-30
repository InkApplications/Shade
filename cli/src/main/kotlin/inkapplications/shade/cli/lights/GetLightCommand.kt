package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.ShadeCommand
import inkapplications.shade.cli.resourceId

class GetLightCommand: ShadeCommand(
    help = "Get data for a specific light"
) {
    private val lightId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val light = shade.lights.getLight(lightId)

        echo("${light.id.value}:")
        echo("    On: ${light.power.on}")

        return 0
    }
}
