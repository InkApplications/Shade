package inkapplications.shade.cli.auth

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import dagger.Reusable
import inkapplications.shade.Shade
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@Reusable
class Validate @Inject constructor(
    private val shade: Shade
): CliktCommand(
    name = "auth:validate",
    help = "Check validity of stored token"
) {
    private val token by option(
        "--token",
        help = "Token value to validate, ex: yOXvTj16z5qx1TWazPXBgZa8vAlgebBmpl5wbxXb"
    )

    override fun run() {
        runBlocking {
            val valid = shade.auth.validateToken(token.orEmpty())
            echo("Valid token: $valid")
        }
    }
}
