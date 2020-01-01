package inkapplications.shade.cli.auth

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.output.TermUi.prompt
import com.github.ajalt.clikt.parameters.options.option
import dagger.Reusable
import inkapplications.shade.Shade
import inkapplications.shade.discover.Device
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@Reusable
class Connect @Inject constructor(
    private val shade: Shade
): CliktCommand(
    name = "connect",
    help = "Connect the Shade CLI to your Hue Device"
) {
    private val url by option(
        "--url",
        help = "A specific bridge URL to connect to. ex. `http://192.168.1.8/`"
    )

    override fun run() {
        runBlocking {
            val url = getUrl() ?: return@runBlocking

            shade.setBaseUrl(url)
            echo("Waiting to connect. Press the button on your Hue Device now.")
            shade.auth.awaitToken()
            echo("Success 🎉")
        }
    }

    private suspend fun getUrl(): String? {
        if (url != null) return url
        val networkDevices = shade.discovery.getDevices()

        when (networkDevices.size) {
            0 -> {
                echo("No bridges found. Try specifying the URL manually with `--url=BRIDGE-URL`")
                return null
            }
            1 -> return networkDevices.first().also {
                echo("Connecting to bridge at ${it.ip}")
            }.ipBaseUrl()
        }

        echo("Multiple Bridges Found.")
        networkDevices.forEachIndexed { index, device ->
            echo("${index + 1}) ${device.id} ${device.ip}")
        }
        val selected = prompt(
            text = "Select a Hue Bridge to connect to [1-${networkDevices.size}]",
            convert = { input ->
                input.toIntOrNull()?.minus(1)?.let { networkDevices[it] }
            })

        return selected?.ipBaseUrl()
    }

    private fun Device.ipBaseUrl() = "http://${this.ip}/"
}
