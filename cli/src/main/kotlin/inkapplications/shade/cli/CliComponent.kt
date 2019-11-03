package inkapplications.shade.cli

import com.github.ajalt.clikt.core.CliktCommand
import dagger.Component
import inkapplications.shade.cli.lights.LightsModule

@Component(modules = [
    LightsModule::class
])
interface CliComponent {
    fun getCommands(): @JvmSuppressWildcards Set<CliktCommand>
}
