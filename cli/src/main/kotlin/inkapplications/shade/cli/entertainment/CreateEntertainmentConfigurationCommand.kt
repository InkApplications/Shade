package inkapplications.shade.cli.entertainment

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.enum
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.entertainmentServiceLocations
import inkapplications.shade.entertainment.parameters.EntertainmentConfigurationCreateParameters
import inkapplications.shade.entertainment.parameters.EntertainmentLocationsCreateParameters
import inkapplications.shade.entertainment.structures.EntertainmentConfigurationMetadata
import inkapplications.shade.entertainment.structures.EntertainmentConfigurationType

object CreateEntertainmentConfigurationCommand: AuthorizedShadeCommand(
    help = "Create a new entertainment configuration on the Hue bridge"
) {
    private val name by argument(
        help = "A human-readable name for the entertainment configuration"
    )

    private val configurationType by argument(
        help = "The type of entertainment configuration (screen, monitor, music, 3dspace, other)"
    ).enum<ConfigurationTypes>()

    private val serviceLocations by option(
        help = "Service locations in format: id:x,y,z;id:x,y,z (e.g. abc-123:0.5,0.5,0.0;def-456:-0.5,0.5,0.0)"
    ).entertainmentServiceLocations()

    override suspend fun runCommand(): Int {
        val response = shade.entertainmentConfig.createConfiguration(
            parameters = EntertainmentConfigurationCreateParameters(
                metadata = EntertainmentConfigurationMetadata(
                    name = name,
                ),
                configurationType = configurationType.type,
                locations = EntertainmentLocationsCreateParameters(
                    serviceLocations = serviceLocations.orEmpty(),
                ),
            )
        )

        logger.debug("Got response: $response")
        echo("Created entertainment configuration: ${response.id}")

        return 0
    }

    private enum class ConfigurationTypes(val type: EntertainmentConfigurationType) {
        Screen(EntertainmentConfigurationType.Screen),
        Monitor(EntertainmentConfigurationType.Monitor),
        Music(EntertainmentConfigurationType.Music),
        ThreeDSpace(EntertainmentConfigurationType.ThreeDSpace),
        Other(EntertainmentConfigurationType.Other),
    }
}
