package inkapplications.shade.cli.scenes

import com.github.ajalt.clikt.core.CliktCommand
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class ScenesModule {
    @Binds
    @IntoSet
    abstract fun scenes(command: ScenesCommand): CliktCommand
}
