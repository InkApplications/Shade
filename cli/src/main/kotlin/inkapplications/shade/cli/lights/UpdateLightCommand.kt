package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.cli.*
import inkapplications.shade.lights.parameters.*
import inkapplications.spondee.measure.mireds
import inkapplications.spondee.measure.toMireds
import inkapplications.spondee.scalar.toWholePercentage
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
        help = "Change the brightness of a light as a function of its current brightness. ie. '+10%'"
    ).percentage()

    private val colorTemperature by option(
        help = "Set the color temperature of a light, in Kelvin. ie. '5600K'"
    ).kelvin()

    private val colorTemperatureDelta by option(
        help = "Change the color temperature of a light as a function of its current temperature, in Mireds only. ie '+100'"
    ).mireds()

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
                    action = if (it.toWholePercentage().value.toDouble() >= 0) DeltaAction.Up else DeltaAction.Down,
                    brightnessDelta = it.toWholePercentage(),
                )
            },
            colorTemperature = colorTemperature?.let {
                ColorTemperatureParameters(
                    temperature = colorTemperature,
                )
            },
            colorTemperatureDelta = colorTemperatureDelta?.let {
                ColorTemperatureDeltaParameters(
                    action = if (it.value.toDouble() >= 0) DeltaAction.Up else DeltaAction.Down,
                    temperatureDelta = it.value.toDouble().absoluteValue.mireds,
                )
            }
        )
        logger.debug("Using Parameters: $parameters")
        val response = shade.lights.updateLight(lightId, parameters)
        logger.info("Got response: $response")

        return 0
    }
}

