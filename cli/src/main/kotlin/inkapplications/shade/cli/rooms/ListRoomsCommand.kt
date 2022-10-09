package inkapplications.shade.cli.rooms

import inkapplications.shade.cli.AuthorizedShadeCommand

object ListRoomsCommand: AuthorizedShadeCommand(
    help = "Get all of the rooms configured on the Hue bridge"
) {
    override suspend fun runCommand(): Int {
        val rooms = shade.rooms.getRooms()

        logger.debug("Got Rooms: $rooms")
        rooms.forEach(::echoRoom)

        return 0
    }
}
