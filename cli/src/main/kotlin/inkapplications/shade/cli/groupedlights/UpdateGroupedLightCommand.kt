package inkapplications.shade.cli.groupedlights

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.cli.*
import inkapplications.shade.groupedlights.parameters.GroupedLightUpdateParameters
import inkapplications.shade.lights.parameters.*
import inkapplications.shade.structures.parameters.PowerParameters
import inkapplications.spondee.measure.mireds
import inkapplications.spondee.scalar.toWholePercentage
import kotlin.math.absoluteValue

object UpdateGroupedLightCommand: AuthorizedShadeCommand(
    help = "Set the state of a specific light group",
) {
    private val id by argument().resourceId()

    private val power by option(
        help = "Set the power on/off state of the light group"
    ).power()

    private val brightness by option(
        help = "Set the brightness of a light group, in whole percentage ie. '50%'"
    ).percentage()

    private val brightnessDelta by option(
        help = "Change the brightness of a light group as a function of its current brightness. ie. '+10%'"
    ).percentage()

    private val colorTemperature by option(
        help = "Set the color temperature of a light group, in Mireds or Kelvin. Kelvin values must end in a 'K'. ie. '5000K' for Kelvin or just '200' for Mireds."
    ).colorTemperature()

    private val colorTemperatureDelta by option(
        help = "Change the color temperature of a light group as a function of its current temperature, in Mireds only. ie '+100'"
    ).mireds()

    private val color by option(
        help = "Change the color of a light group. Accepts CSS-style values such as RGB or Hex strings."
    ).color()

    private val dynamicDuration by option(
        help = "Set a transition time dynamic for the new state."
    ).duration()

    private val dynamicSpeed by option(
        help = "Set a speed dynamic for the new state, in whole percentage. ie. '50%'"
    ).percentage()

    private val alert by option(
        help = "Set an alert effect on the light group"
    ).alertEffect()

    private val gradientColors by option(
        help = "Set a list of gradient point colors. A Minimum of 2 points are required. Accepts CSS-style values such as RGB or Hex strings. Comma separated."
    ).gradientPoints()

    override suspend fun runCommand(): Int {
        val parameters = GroupedLightUpdateParameters(
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
            },
            color = color?.let {
                ColorParameters(
                    color = it,
                )
            },
            dynamics = if (dynamicDuration != null || dynamicSpeed != null) {
                DynamicsParameters(
                    duration = dynamicDuration,
                    speed = dynamicSpeed,
                )
            } else null,
            alert = alert?.let {
                AlertParameters(
                    action = it,
                )
            },
        )
        logger.debug("Using Parameters: $parameters")
        val response = shade.groupedLights.updateGroup(id, parameters)
        logger.info("Got response: $response")

        return 0
    }
}

