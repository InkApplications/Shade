package inkapplications.shade.cli.groups

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
import inkapplications.shade.constructs.percent
import inkapplications.shade.groups.GroupStateModification
import kotlinx.coroutines.runBlocking
import org.threeten.bp.Duration
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
        help = "Change the brightness as a percentage from 0 to 100"
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
            shade.groups.setState(group, GroupStateModification(
                on = on,
                brightness = brightness?.percent,
                colorTemperature = colorTemperature?.kelvin,
                transitionTime = transitionTime?.let(Duration::ofMillis)
            ))

        }
    }
}
