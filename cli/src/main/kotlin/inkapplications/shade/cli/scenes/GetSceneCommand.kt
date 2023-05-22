package inkapplications.shade.cli.scenes

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object GetSceneCommand: AuthorizedShadeCommand(
    help = "Get information for a specific Scene",
) {
    private val id by argument().resourceId()

    override suspend fun runCommand(): Int {
        val scene = shade.scenes.getScene(id)

        echoScene(scene)

        return 0
    }
}
