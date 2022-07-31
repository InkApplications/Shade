package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.ShadeCommand
import inkapplications.shade.cli.resourceId
import inkapplications.spondee.measure.Kelvin
import inkapplications.spondee.measure.toTemperature

class GetLightCommand: ShadeCommand(
    help = "Get data for a specific light"
) {
    private val lightId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val light = shade.lights.getLight(lightId)

        echo("${light.id.value}:")
        echo("    On: ${light.powerInfo.on}")
        val temperature = light.colorTemperatureInfo
        if (temperature != null) {
            val temperatureString = temperature.temperature?.toTemperature()?.let(Kelvin::format) ?: "--"
            echo("    Temperature: $temperatureString")
            echo("    Temperature Range: ${temperature.range}")
        }

        return 0
    }
}
