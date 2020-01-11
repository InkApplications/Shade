package inkapplications.shade.cli.scenes

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import dagger.Reusable
import inkapplications.shade.Shade
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@Reusable class LightSceneUpdate @Inject constructor(
    private val shade: Shade
): CliktCommand(
    name = "scenes:update:lights",
    help = "Update the properties of a Light Scene"
) {
    private val id by argument(
        help = "The ID of the Light Scene to be modified"
    )
    private val name by option(
        help = "A friendly name to call the scene"
    )

    private val lights by option(
        help = "A comma separated list of Light ID's to add to the Scene"
    )

    override fun run() {
        runBlocking {
            shade.scenes.updateLightScene(
                id = id,
                name = name,
                lights = lights?.split(",")
            )
        }
    }
}
