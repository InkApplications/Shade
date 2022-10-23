package inkapplications.shade.cli.rooms

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.deviceResourceReferences
import inkapplications.shade.cli.roomArchetype
import inkapplications.shade.rooms.parameters.RoomCreateParameters
import inkapplications.shade.structures.SegmentMetadata

object CreateRoomCommand: AuthorizedShadeCommand(
    help = "Create a new room on the Hue bridge"
) {
    val name by argument(
        help = "A human-readable name for the room"
    )

    val archetype by argument(
        help = "The type of room"
    ).roomArchetype()

    val childrenDeviceIds by option(
        help = "A comma-separated list of device ID's to add as children for the room."
    ).deviceResourceReferences()

    override suspend fun runCommand(): Int {
        val response = shade.rooms.createRoom(
            parameters = RoomCreateParameters(
                metadata = SegmentMetadata(
                    archetype = archetype,
                    name = name,
                ),
                children = childrenDeviceIds.orEmpty(),
            )
        )

        logger.debug("Got response $response")

        return 0
    }
}
