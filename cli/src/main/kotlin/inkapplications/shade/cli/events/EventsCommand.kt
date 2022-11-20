package inkapplications.shade.cli.events

import com.github.ajalt.clikt.output.TermUi
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.core.events
import inkapplications.shade.lights.events.LightEvent
import inkapplications.shade.structures.UndocumentedApi
import inkapplications.shade.structures.UnknownEvent
import inkapplications.spondee.scalar.toWholePercentage
import inkapplications.spondee.structure.format
import shade.events.events

@OptIn(UndocumentedApi::class)
object EventsCommand: AuthorizedShadeCommand(
    help = "Open a stream of events from the hue bridge (experimental)"
) {
    override suspend fun runCommand(): Int {
        shade.events.bridgeEvents().collect { events ->
            events.forEach { event ->
                when (event) {
                    is LightEvent -> echoLight(event)
                    is UnknownEvent -> echoUnknown(event)
                    else -> {
                        echo("Unhandled Event:")
                        echo(event.toString())
                    }
                }
            }
        }

        throw IllegalStateException("Event Stream closed")
    }

    private fun echoLight(event: LightEvent) {
        echo("light[${event.id.value}]:")
        event.powerInfo?.run {
            echo("    On: $on")
        }
        event.colorTemperatureInfo?.run {
            val temperatureString = temperature?.toKelvin()?.format() ?: "--"
            TermUi.echo("    Temperature: $temperatureString")
        }
        event.dimmingInfo?.run {
            TermUi.echo("    Brightness: ${brightness.toWholePercentage().format()}")
        }
        event.colorInfo?.run {
            TermUi.echo("    Color (rgb): ${color.toSRGB().toHex()}")
            TermUi.echo("    Color (xy): [${color.toXYZ().toCIExyY().x},${color.toXYZ().toCIExyY().y}]")
        }
    }

    private fun echoUnknown(event: UnknownEvent) {
        echo("${event.type}:")
        echo("    data: ${event.json}")
    }
}
