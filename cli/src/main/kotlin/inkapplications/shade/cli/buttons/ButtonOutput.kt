package inkapplications.shade.cli.buttons

import com.github.ajalt.clikt.core.CliktCommand
import inkapplications.shade.button.structures.Button

fun CliktCommand.echoButton(button: Button) {
    echo("${button.id.value}:")
    echo("    Owner: ${button.owner}")
    echo("    Control ID: ${button.metadata.controlId}")
    val report = button.state.report
    if (report != null) {
        echo("    Last Event: ${report.event}")
        echo("    Updated: ${report.updated}")
    }
    val repeatInterval = button.state.repeatInterval
    if (repeatInterval != null) {
        echo("    Repeat Interval: ${repeatInterval}ms")
    }
    echo("    Events:")
    button.state.events.forEach { event ->
        echo("     - $event")
    }
}

