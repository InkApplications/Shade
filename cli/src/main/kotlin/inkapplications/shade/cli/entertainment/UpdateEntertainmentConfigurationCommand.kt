package inkapplications.shade.cli.entertainment

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.enum
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.entertainmentServiceLocationsUpdate
import inkapplications.shade.cli.resourceId
import inkapplications.shade.entertainment.parameters.EntertainmentConfigurationUpdateParameters
import inkapplications.shade.entertainment.parameters.EntertainmentLocationsUpdateParameters
import inkapplications.shade.entertainment.structures.EntertainmentConfigurationAction
import inkapplications.shade.entertainment.structures.EntertainmentConfigurationMetadata
import inkapplications.shade.entertainment.structures.EntertainmentConfigurationType

object UpdateEntertainmentConfigurationCommand: AuthorizedShadeCommand(
    help = "Update an existing entertainment configuration on the Hue bridge"
) {
    private val configurationId by argument(
        help = "The ID of the entertainment configuration to update"
    ).resourceId()

    private val name by option(
        help = "A human-readable name for the entertainment configuration"
    )

    private val configurationType by option(
        help = "The type of entertainment configuration (screen, monitor, music, 3dspace, other)"
    ).enum<ConfigurationTypes>()

    private val action by option(
        help = "Action to control streaming (start, stop)"
    ).enum<StreamActions>()

    private val serviceLocations by option(
        help = "Service locations in format: id:x,y,z[:equalizationFactor];id:x,y,z[:equalizationFactor] (e.g. abc-123:0.5,0.5,0.0;def-456:-0.5,0.5,0.0:0.8)"
    ).entertainmentServiceLocationsUpdate()

    override suspend fun runCommand(): Int {
        val metadata = if (name != null) {
            EntertainmentConfigurationMetadata(name = name!!)
        } else {
            null
        }

        val locations = if (serviceLocations != null) {
            EntertainmentLocationsUpdateParameters(serviceLocations = serviceLocations!!)
        } else {
            null
        }

        val response = shade.entertainmentConfig.updateConfiguration(
            id = configurationId,
            parameters = EntertainmentConfigurationUpdateParameters(
                metadata = metadata,
                configurationType = configurationType?.type,
                action = action?.action,
                locations = locations,
            )
        )

        logger.debug("Got response: $response")
        echo("Updated entertainment configuration: ${response.id}")

        return 0
    }

    private enum class ConfigurationTypes(val type: EntertainmentConfigurationType) {
        Screen(EntertainmentConfigurationType.Screen),
        Monitor(EntertainmentConfigurationType.Monitor),
        Music(EntertainmentConfigurationType.Music),
        ThreeDSpace(EntertainmentConfigurationType.ThreeDSpace),
        Other(EntertainmentConfigurationType.Other),
    }

    private enum class StreamActions(val action: EntertainmentConfigurationAction) {
        Start(EntertainmentConfigurationAction.Start),
        Stop(EntertainmentConfigurationAction.Stop),
    }
}

