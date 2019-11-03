package inkapplications.shade.cli.lights

import com.github.ajalt.clikt.core.CliktCommand
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class LightsModule {
    @Binds @IntoSet abstract fun list(command: ListLights): CliktCommand
    @Binds @IntoSet abstract fun control(command: LightControl): CliktCommand
}
