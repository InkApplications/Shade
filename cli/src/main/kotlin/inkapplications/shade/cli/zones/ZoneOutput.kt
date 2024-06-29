package inkapplications.shade.cli.zones

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.output.TermUi
import inkapplications.shade.zones.structures.Zone

fun CliktCommand.echoZone(zone: Zone) {
    echo("${zone.id.value}:")
    echo("    Name: ${zone.metadata.name}")
    echo("    Archetype: ${zone.metadata.archetype}")
    echo("    Children:")
    zone.children.forEach {
        echo("     - $it")
    }
    echo("    Services:")
    zone.services.forEach {
        echo("     - $it")
    }
}
