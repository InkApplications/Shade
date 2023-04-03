package inkapplications.shade.cli.rooms

import com.github.ajalt.clikt.output.TermUi
import inkapplications.shade.rooms.structures.Room

fun echoRoom(room: Room) {
    TermUi.echo("${room.id.value}:")
    TermUi.echo("    Name: ${room.metadata.name}")
    TermUi.echo("    Archetype: ${room.metadata.archetype}")
    TermUi.echo("    Children:")
    room.children.forEach {
        TermUi.echo("     - $it")
    }
    TermUi.echo("    Services:")
    room.services.forEach {
        TermUi.echo("     - $it")
    }
}
