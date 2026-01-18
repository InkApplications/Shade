package inkapplications.shade.cli.lightlevels

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.enum
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId
import inkapplications.shade.lightlevel.parameters.LightLevelUpdateParameters

object UpdateLightLevelCommand: AuthorizedShadeCommand(
    help = "Update a light level sensor on the Hue bridge"
) {
    private val lightLevelId by argument(
        help = "The ID of the light level sensor to be updated"
    ).resourceId()

    private val enable by option(
        help = "Enable the light level sensor"
    ).enum<EnableState>()

    override suspend fun runCommand(): Int {
        val enabled = when(enable) {
            EnableState.Enable -> true
            EnableState.Disable -> false
            null -> null
        }

        val response = shade.lightLevels.updateLightLevel(
            id = lightLevelId,
            parameters = LightLevelUpdateParameters(
                enabled = enabled,
            ),
        )

        logger.debug("Got response $response")

        return 0
    }

    enum class EnableState {
        Enable,
        Disable
    }
}
