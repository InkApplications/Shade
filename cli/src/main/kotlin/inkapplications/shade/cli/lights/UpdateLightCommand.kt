package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.cli.*
import inkapplications.shade.lights.parameters.*
import inkapplications.spondee.scalar.WholePercentage
import inkapplications.spondee.structure.value
import kotlin.math.absoluteValue

object UpdateLightCommand: AuthorizedShadeCommand(
    help = "Set the state of a specific light",
) {
    private val lightId by argument().resourceId()

    private val power by option(
        help = "Set the power on/off state of the light"
    ).power()

    private val brightness by option(
        help = "Set the brightness of a light, in whole percentage ie. '50%'"
    ).percentage()

    private val brightnessDelta by option(
        help = "Change the brightness of a light as a function of its current brightness. ie. +10%"
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
                    brightness = it,
                )
            },
            dimmingDelta = brightnessDelta?.let {
                DimmingDeltaParameters(
                    action = if (it.value(WholePercentage) >= 0) DeltaAction.Up else DeltaAction.Down,
                    brightnessDelta = WholePercentage.of(it.value(WholePercentage).absoluteValue),
                )
            }
        )
        logger.debug("Using Parameters: $parameters")
        val response = shade.lights.updateLight(lightId, parameters)
        logger.info("Got response: $response")

        return 0
    }
}

