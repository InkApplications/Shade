package inkapplications.shade.cli.groups

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.switch
import com.github.ajalt.clikt.parameters.types.float
import com.github.ajalt.clikt.parameters.types.int
import dagger.Reusable
import inkapplications.shade.Shade
import inkapplications.shade.constructs.mireds
import inkapplications.shade.constructs.percentageBrightness
import inkapplications.shade.groups.GroupStateModification
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@Reusable
class GroupsControl @Inject constructor(
    private val shade: Shade
): CliktCommand(
    name = "groups:control",
    help = "Change the state of a light group"
) {
    private val group by argument()

    private val on: Boolean? by option(
        help = "Turn on/off all lights in the group"
    ).switch(
        "--on" to true,
        "--off" to false
    )

    private val brightness: Float? by option(
        help = "Change the brightness as a percentage on a scale of 0.0 to 1.0"
    ).float()

    private val colorTemperature: Int? by option(
        "--color-temperature",
        help = "Change the color temperature in Mireds"
    ).int()

    override fun run() {
        runBlocking {
            shade.groups.setState(group, GroupStateModification(
                on = on,
                brightness = brightness?.percentageBrightness,
                colorTemperature = colorTemperature?.mireds
            ))
        }
    }
}
