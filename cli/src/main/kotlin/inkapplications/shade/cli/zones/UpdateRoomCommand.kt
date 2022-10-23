package inkapplications.shade.cli.zones

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.deviceResourceReferences
import inkapplications.shade.cli.resourceId
import inkapplications.shade.cli.segmentArchetype
import inkapplications.shade.structures.SegmentMetadataUpdate
import inkapplications.shade.zones.parameters.ZoneUpdateParameters

object UpdateZoneCommand: AuthorizedShadeCommand(
    help = "Update an existing zone on the Hue bridge",
) {
    private val zoneId by argument(
        help = "The ID of the zone resource to be updated"
    ).resourceId()

    private val name by option(
        help = "A human-readable name for the zone"
    )

    private val archetype by option(
        help = "The type of zone"
    ).segmentArchetype()

    private val childrenDeviceIds by option(
        help = "A comma-separated list of device ID's to add as children for the zone."
    ).deviceResourceReferences()

    override suspend fun runCommand(): Int {
        val response = shade.zones.updateZone(
            id = zoneId,
            parameters = ZoneUpdateParameters(
                metadata = if (archetype != null || name != null) SegmentMetadataUpdate(
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
