package inkapplications.shade.cli.buttons

import com.github.ajalt.clikt.parameters.arguments.argument
import inkapplications.shade.cli.AuthorizedShadeCommand
import inkapplications.shade.cli.resourceId

object GetButtonCommand: AuthorizedShadeCommand(
    help = "Get data for a specific button"
) {
    private val buttonId by argument().resourceId()

    override suspend fun runCommand(): Int {
        val button = shade.buttons.getButton(buttonId)

        logger.debug("Got Button: $button")
        echoButton(button)

        return 0
    }
}
