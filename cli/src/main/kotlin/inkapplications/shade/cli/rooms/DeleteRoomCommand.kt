package inkapplications.shade.cli.rooms

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object DeleteRoomCommand: AuthorizedShadeCommand(
    help = "Delete a room from the hue bridge"
) {
    private val roomId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val response = shade.rooms.deleteRoom(roomId)

        logger.debug("Got response: $response")

        return 0
    }
}
