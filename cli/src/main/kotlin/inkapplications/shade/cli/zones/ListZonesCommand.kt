package inkapplications.shade.cli.zones

import inkapplications.shade.cli.AuthorizedShadeCommand

object ListZonesCommand: AuthorizedShadeCommand(
    help = "Get all of the zones configured on the Hue bridge"
) {
    override suspend fun runCommand(): Int {
        val zones = shade.zones.listZones()

        logger.debug("Got Zones: $zones")
        zones.forEach(::echoZone)

        return 0
    }
}
