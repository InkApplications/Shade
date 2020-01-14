package inkapplications.shade.cli.scenes

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import dagger.Reusable
import inkapplications.shade.Shade
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@Reusable class SceneDelete @Inject constructor(
    private val shade: Shade
): CliktCommand(
    name = "scenes:delete",
    help = "Delete a scene from the bridge."
) {
    private val id by argument(
        help = "The ID of the scene to be deleted"
    )

    override fun run() {
        runBlocking {
            shade.scenes.deleteScene(id)
        }
    }
}
