package inkapplications.shade.cli.buttons

import inkapplications.shade.cli.AuthorizedShadeCommand

object ListButtonsCommand: AuthorizedShadeCommand(
    help = "Get all of the buttons configured on the Hue bridge"
) {
    override suspend fun runCommand(): Int {
        val buttons = shade.buttons.listButtons()

        logger.debug("Got Buttons: $buttons")
        buttons.forEach(::echoButton)

        return 0
    }
}
