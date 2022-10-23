package inkapplications.shade.cli.zones

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.deviceResourceReferences
import inkapplications.shade.cli.segmentArchetype
import inkapplications.shade.structures.SegmentMetadata
import inkapplications.shade.zones.parameters.ZoneCreateParameters

object CreateZoneCommand: AuthorizedShadeCommand(
    help = "Create a new zone on the Hue bridge"
) {
    val name by argument(
        help = "A human-readable name for the zone"
    )

    val archetype by argument(
        help = "The type of zone"
    ).segmentArchetype()

    val childrenDeviceIds by option(
        help = "A comma-separated list of device ID's to add as children for the zone."
    ).deviceResourceReferences()

    override suspend fun runCommand(): Int {
        val response = shade.zones.createZone(
            parameters = ZoneCreateParameters(
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
