package inkapplications.shade.cli.scenes

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.convert
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.cli.*
import inkapplications.shade.scenes.parameters.SceneCreateParameters
import inkapplications.shade.scenes.structures.SceneAction
import inkapplications.shade.scenes.structures.SceneActionReference
import inkapplications.shade.scenes.structures.SceneMetadata
import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.ResourceType

object CreateSceneCommand: AuthorizedShadeCommand(
    help = "Create a new Scene",
) {
    val name by argument(
        help = "A human-readable name for the scene"
    )

    val group by argument(
        help = "The ID of the group to apply the scene to",
    ).resourceId()

    val lights by argument(
        help = "A comma separated list of light IDs that define the order of each light in the group to which the supplied actions are applied. Every light in the group must be supplied here.",
    ).resourceReferences().convert {
        it.map { ResourceReference(it, ResourceType.Light) }
    }

    val powerActions by option(
        help = "A comma separated list of the power states ('on'/'true', 'off'/'false', 'null') for each action applied to the scene.",
    ).powerValues()

    val dimmingActions by option(
        help = "A comma separated list of the brightness state, as whole percentages, for each action applied to the scene.",
    ).dimmingValues()

    val colorTempeartureActions by option(
        help = "A comma separated list of the color temperatures, in kelvin, for each action applied to the scene.",
    ).colorTemperatureValues()

    val colorActions by option(
        help = "A comma separated list of the color values, in hex, for each action applied to the scene.",
    ).colorValues()

    val effectActions by option(
        help = "A comma separated list of light effect keys, for each action applied to the scene.",
    ).lightEffects()

    val dynamicsActions by option(
        help = "A comma separated list of light transition or timed effects to apply to each action in the scene."
    ).durations()

    val groupResourceType by option(
        help = "The type of resource the group is",
    ).convert {
        ResourceType(it)
    }.default(ResourceType.Room)

    override suspend fun runCommand(): Int {
        assertMatchesLightSize(powerActions, "The number of power actions must match the number of light ID's supplied.")
        assertMatchesLightSize(dimmingActions, "The number of dimming actions must match the number of light ID's supplied.")
        assertMatchesLightSize(colorTempeartureActions, "The number of color temperature actions must match the number of light ID's supplied.")
        assertMatchesLightSize(colorActions, "The number of color actions must match the number of light ID's supplied.")
        assertMatchesLightSize(effectActions, "The number of effect actions must match the number of light ID's supplied.")
        assertMatchesLightSize(dynamicsActions, "The number of dynamics must match the number of light ID's supplied.")

        val actions = lights.mapIndexed { index, lightReference ->
            SceneActionReference(
                target = lightReference,
                action = SceneAction(
                    powerValue = powerActions?.get(index),
                    dimmingValue = dimmingActions?.get(index),
                    colorTemperatureValue = colorTempeartureActions?.get(index),
                    colorValue = colorActions?.get(index),
                    effect = effectActions?.get(index),
                    dynamicsDuration = dynamicsActions?.get(index),
                )
            )
        }
        val response = shade.scenes.createScene(
            parameters = SceneCreateParameters(
                metadata = SceneMetadata(
                    name = name,
                ),
                actions = actions,
                group = ResourceReference(
                    id = group,
                    type = groupResourceType,
                )
            )
        )

        logger.debug("Got response $response")

        echo("$response")

        return 0
    }

    private fun assertMatchesLightSize(list: List<*>?, message: String) {
        if (list != null && list.size != lights.size) {
            throw IllegalArgumentException(message)
        }
    }
}
