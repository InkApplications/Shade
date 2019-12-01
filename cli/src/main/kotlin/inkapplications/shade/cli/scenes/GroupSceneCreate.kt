package inkapplications.shade.cli.scenes

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import dagger.Reusable
import inkapplications.shade.Shade
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@Reusable class GroupSceneCreate @Inject constructor(
    private val shade: Shade
): CliktCommand(
    name = "scenes:create:group",
    help = "Create a new scene for a Group"
) {
    private val name by argument(
        help = "A friendly name to call the scene"
    )

    private val group by argument(
        help = "A Group ID to associate with the Scene"
    )

    override fun run() {
        runBlocking {
            val created = shade.scenes.createGroupScene(
                name = name,
                group = group
            )
            echo("Created Scene: $created")
        }
    }
}
