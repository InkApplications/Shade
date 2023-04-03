package inkapplications.shade.cli.zones

import com.github.ajalt.clikt.output.TermUi
import inkapplications.shade.zones.structures.Zone

fun echoZone(zone: Zone) {
    TermUi.echo("${zone.id.value}:")
    TermUi.echo("    Name: ${zone.metadata.name}")
    TermUi.echo("    Archetype: ${zone.metadata.archetype}")
    TermUi.echo("    Children:")
    zone.children.forEach {
        TermUi.echo("     - $it")
    }
    TermUi.echo("    Services:")
    zone.services.forEach {
        TermUi.echo("     - $it")
    }
}
