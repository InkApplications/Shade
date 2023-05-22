package inkapplications.shade.cli.scenes

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object DeleteSceneCommand: AuthorizedShadeCommand(
    help = "Delete a scene from the hue bridge"
) {
    private val sceneId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val response = shade.scenes.deleteScene(sceneId)

        logger.debug("Got response: $response")

        echo(response)

        return 0
    }
}
