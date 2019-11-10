package inkapplications.shade.cli.auth

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import dagger.Reusable
import inkapplications.shade.Shade
import inkapplications.shade.config.ShadeConfig
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@Reusable
class Connect @Inject constructor(
    private val storage: FileStorage
): CliktCommand(
    name = "connect",
    help = "Connect the Shade CLI to your Hue Device"
) {
    private val url by argument()

    override fun run() {
        runBlocking {
            storage.setUrl(url)
            echo("Waiting to connect. Press the button on your Hue Device now.")
            Shade(ShadeConfig(url, storage.getAppId()), storage).auth.awaitToken()
            echo("Success 🎉")
        }
    }
}
