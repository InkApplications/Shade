package inkapplications.shade.cli.homekit

import com.github.ajalt.clikt.core.CliktCommand
import inkapplications.shade.homekit.structures.Homekit

fun CliktCommand.echoHomekit(homekit: Homekit) {
    echo("${homekit.id.value}:")
    echo("    Type: ${homekit.type}")
    echo("    Status: ${homekit.status}")
}
