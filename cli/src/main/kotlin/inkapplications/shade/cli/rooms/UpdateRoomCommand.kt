package inkapplications.shade.cli.rooms

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.deviceResourceReferences
import inkapplications.shade.cli.resourceId
import inkapplications.shade.cli.roomArchetype
import inkapplications.shade.rooms.parameters.RoomUpdateParameters
import inkapplications.shade.rooms.structures.RoomMetadataUpdateParameters

object UpdateRoomCommand: AuthorizedShadeCommand(
    help = "Update an existing room on the Hue bridge"
) {
    private val roomId by argument(
        help = "The ID of the room resource to be updated"
    ).resourceId()

    private val name by option(
        help = "A human-readable name for the room"
    )

    private val archetype by option(
        help = "The type of room"
    ).roomArchetype()

    private val childrenDeviceIds by option(
        help = "A comma-separated list of device ID's to add as children for the room."
    ).deviceResourceReferences()

    override suspend fun runCommand(): Int {
        val response = shade.rooms.updateRoom(
            id = roomId,
            parameters = RoomUpdateParameters(
                metadata = if (archetype != null || name != null) RoomMetadataUpdateParameters(
                    archetype = archetype,
                    name = name,
                ) else null,
                children = childrenDeviceIds,
            ),
        )

        logger.debug("Got response $response")

        return 0
    }
}
