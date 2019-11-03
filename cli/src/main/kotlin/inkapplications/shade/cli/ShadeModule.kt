package inkapplications.shade.cli

import dagger.Module
import dagger.Provides
import dagger.Reusable
import inkapplications.shade.Shade
import inkapplications.shade.cli.auth.FileStorage
import inkapplications.shade.config.ShadeConfig

@Module
class ShadeModule {
    @Provides
    @Reusable
    fun shade(fileStorage: FileStorage): Shade {
        val url = fileStorage.getUrl()
        return Shade(ShadeConfig(url , fileStorage.getAppId()), fileStorage)
    }
}
