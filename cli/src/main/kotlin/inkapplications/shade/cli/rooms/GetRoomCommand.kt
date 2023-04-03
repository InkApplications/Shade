package inkapplications.shade.cli.rooms

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object GetRoomCommand: AuthorizedShadeCommand(
    help = "Get data for a specific room"
) {
    private val roomId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val room = shade.rooms.getRoom(roomId)

        logger.debug("Got Room: $room")
        echoRoom(room)

        return 0
    }
}
