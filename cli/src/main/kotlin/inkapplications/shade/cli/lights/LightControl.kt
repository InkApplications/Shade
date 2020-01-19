package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.switch
import com.github.ajalt.clikt.parameters.types.int
import dagger.Reusable
import inkapplications.shade.Shade
import inkapplications.shade.constructs.mireds
import inkapplications.shade.lights.LightStateModification
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@Reusable class LightControl @Inject constructor(
    private val shade: Shade
): CliktCommand(
    name = "lights:control",
    help = "Change the state of a light"
) {
    private val light by argument()

    private val on: Boolean? by option(
        help = "Change the light state to on or off"
    ).switch(
        "--on" to true,
        "--off" to false
    )

    private val brightness: Int? by option(
        help = "Change the brightness on a scale of 0 to 254"
    ).int()

    private val colorTemperature: Int? by option(
        "--color-temperature",
        help = "Change the color temperature in Mireds"
    ).int()

    override fun run() {
        runBlocking {
            val modification = LightStateModification(
                on = on,
                brightness = brightness,
                colorTemperature = colorTemperature?.mireds
            )
            shade.lights.setState(light, modification)
        }
    }
}
