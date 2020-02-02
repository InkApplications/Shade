package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.switch
import com.github.ajalt.clikt.parameters.types.float
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.types.long
import dagger.Reusable
import inkapplications.shade.Shade
import inkapplications.shade.constructs.kelvin
import inkapplications.shade.constructs.percentage
import inkapplications.shade.lights.LightStateModification
import kotlinx.coroutines.runBlocking
import org.threeten.bp.Duration
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

    private val brightness: Float? by option(
        help = "Change the brightness as a percentage from 0.0 to 1.0"
    ).float()

    private val colorTemperature: Int? by option(
        "--color-temperature",
        help = "Change the color temperature in Kelvin"
    ).int()

    private val transitionTime: Long? by option(
        "--transition-time",
        help = "The time in milliseconds it should take to transition to the specified state"
    ).long()

    override fun run() {
        runBlocking {
            val modification = LightStateModification(
                on = on,
                brightness = brightness?.percentage,
                colorTemperature = colorTemperature?.kelvin,
                transitionTime = transitionTime?.let(Duration::ofMillis)
            )
            shade.lights.setState(light, modification)
        }
    }
}
