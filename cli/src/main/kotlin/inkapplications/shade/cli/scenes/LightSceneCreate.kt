package inkapplications.shade.cli.scenes

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import dagger.Reusable
import inkapplications.shade.Shade
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@Reusable class LightSceneCreate @Inject constructor(
    private val shade: Shade
): CliktCommand(
    name = "scenes:create:lights",
    help = "Create a new scene for a list of lights"
) {
    private val name by argument(
        help = "A friendly name to call the scene"
    )

    private val lights by argument(
        help = "A comma separated list of Light ID's to add to the Scene"
    )

    override fun run() {
        runBlocking {
            val created = shade.scenes.createLightScene(
                name = name,
                lights = lights.split(",")
            )
            echo("Created Scene: $created")
        }
    }
}
