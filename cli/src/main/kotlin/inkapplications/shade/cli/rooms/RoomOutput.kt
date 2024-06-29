package inkapplications.shade.cli.rooms

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.output.TermUi
import inkapplications.shade.rooms.structures.Room

fun CliktCommand.echoRoom(room: Room) {
    echo("${room.id.value}:")
    echo("    Name: ${room.metadata.name}")
    echo("    Archetype: ${room.metadata.archetype}")
    echo("    Children:")
    room.children.forEach {
        echo("     - $it")
    }
    echo("    Services:")
    room.services.forEach {
        echo("     - $it")
    }
}
