package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.core.CliktCommand
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ListLights @Inject constructor(

): CliktCommand(
    name = "lights:list",
    help = "List out information about hue lights"
) {
    override fun run() {
        echo("💡 Hue Lights:")
    }
}
