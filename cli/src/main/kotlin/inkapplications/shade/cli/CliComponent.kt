package inkapplications.shade.cli

import com.github.ajalt.clikt.core.CliktCommand
import dagger.Component
import inkapplications.shade.cli.auth.AuthModule
import inkapplications.shade.cli.groups.GroupsModule
import inkapplications.shade.cli.lights.LightsModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AuthModule::class,
    GroupsModule::class,
    LightsModule::class,
    ShadeModule::class
])
interface CliComponent {
    fun getCommands(): @JvmSuppressWildcards Set<CliktCommand>
}
