package inkapplications.shade.cli.scenes

import com.github.ajalt.clikt.core.CliktCommand
import dagger.Reusable
import inkapplications.shade.Shade
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@Reusable class ScenesCommand @Inject constructor(
    private val shade: Shade
): CliktCommand(
    name = "scenes:list",
    help = "List out all scenes known to the hue bridge"
) {
    override fun run() {
        runBlocking {
            shade.scenes.getScenes().forEach { (id, scene) ->
                echo("$id:")
                echo("  name: ${scene.name}")
                echo("  type: ${scene.javaClass.simpleName}")
            }
        }
    }
}
