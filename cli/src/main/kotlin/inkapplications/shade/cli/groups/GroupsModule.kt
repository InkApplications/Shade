package inkapplications.shade.cli.groups

import com.github.ajalt.clikt.core.CliktCommand
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class GroupsModule {
    @Binds
    @IntoSet
    abstract fun list(command: GroupsList): CliktCommand

    @Binds
    @IntoSet
    abstract fun control(command: GroupsControl): CliktCommand
}
