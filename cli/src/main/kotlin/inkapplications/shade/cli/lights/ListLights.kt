package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.core.CliktCommand
import dagger.Reusable
import inkapplications.shade.Shade
import javax.inject.Inject
import kotlinx.coroutines.runBlocking

@Reusable
class ListLights @Inject constructor(
    private val shade: Shade
): CliktCommand(
    name = "lights:list",
    help = "List out information about hue lights"
) {
    override fun run() {
        runBlocking {
            echo("💡 Hue Lights:")
            shade.lights.getLights().forEach { (id, light) ->
                println("    ${light.name}")
            }
        }
    }
}
