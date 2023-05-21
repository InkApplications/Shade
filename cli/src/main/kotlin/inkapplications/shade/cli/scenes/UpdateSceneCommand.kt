package inkapplications.shade.cli.scenes

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.option
import inkapplications.shade.cli.*
import inkapplications.shade.scenes.parameters.SceneUpdateParameters
import inkapplications.shade.scenes.structures.SceneAction
import inkapplications.shade.scenes.structures.SceneActionReference
import inkapplications.shade.scenes.structures.SceneMetadata
import inkapplications.shade.structures.ResourceReference
import inkapplications.shade.structures.ResourceType

object UpdateSceneCommand: AuthorizedShadeCommand(
    help = "Update an existing Scene",
) {
    val sceneId by argument(
        help = "The ID of the scene to update",
    ).resourceId()

    val name by option(
        help = "A human-readable name for the scene"
    )

    val lights by option(
        help = "A comma separated list of light IDs that define the order of each light in the group to which the supplied actions are applied. Every light in the group must be supplied here.",
    ).resourceIds().convert {
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

    override suspend fun runCommand(): Int {
        assertSameSize(lights, powerActions, "The number of power actions must match the number of light ID's supplied.")
        assertSameSize(lights, dimmingActions, "The number of dimming actions must match the number of light ID's supplied.")
        assertSameSize(lights, colorTempeartureActions, "The number of color temperature actions must match the number of light ID's supplied.")
        assertSameSize(lights, colorActions, "The number of color actions must match the number of light ID's supplied.")
        assertSameSize(lights, effectActions, "The number of effect actions must match the number of light ID's supplied.")
        assertSameSize(lights, dynamicsActions, "The number of dynamics must match the number of light ID's supplied.")

        val actions = lights?.mapIndexed { index, lightReference ->
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
        val response = shade.scenes.updateScene(
            id = sceneId,
            parameters = SceneUpdateParameters(
                metadata = name?.let {
                    SceneMetadata(
                        name = it,
                    )
                },
                actions = actions,
            )
        )

        logger.debug("Got response $response")

        echo("$response")

        return 0
    }
}
