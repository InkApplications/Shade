package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.cli.ShadeCommand
import inkapplications.shade.cli.percentage
import inkapplications.shade.cli.power
import inkapplications.shade.cli.resourceId
import inkapplications.shade.lights.parameters.DimmingParameters
import inkapplications.shade.lights.parameters.LightUpdateParameters
import inkapplications.shade.lights.parameters.PowerParameters

object UpdateLightCommand: ShadeCommand(
    help = "Set the state of a specific light",
) {
    private val lightId by argument().resourceId()

    private val power by option(
        help = "Set the power on/off state of the light"
    ).power()

    private val brightness by option(
        help = "Set the brightness of a light, in whole percentage ie. '50%'"
    ).percentage()

    override suspend fun runCommand(): Int {
        val parameters = LightUpdateParameters(
            power = power?.let {
                PowerParameters(
                    on = it,
                )
            },
            dimming = brightness?.let {
                DimmingParameters(
                    brightness = it
                )
            },
        )
        debug { echo(parameters) }
        shade.lights.updateLight(lightId, parameters)

        return 0
    }
}

