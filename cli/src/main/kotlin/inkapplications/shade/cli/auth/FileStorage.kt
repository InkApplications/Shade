package inkapplications.shade.cli.auth

import inkapplications.shade.auth.TokenStorage
import java.io.File
import java.util.*
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class FileStorage @Inject constructor(): TokenStorage {
    private val file = File("cli-config.properties")
    private val current: AtomicReference<Properties> by lazy {
        Properties()
            .apply {
                if (file.exists()) file.inputStream().run(::load)
            }
            .let(::AtomicReference)
    }

    override suspend fun setToken(token: String?) {
        current.get().apply {
            setProperty("token", token)
            store(file.outputStream(), null)
        }
    }

    override suspend fun getToken(): String? = current.get().getProperty("token")

    fun getUrl(): String = current.get().getProperty("url") ?: "https://0.0.0.0/"

    fun setUrl(url: String) {
        current.get().apply {
            setProperty("url", url)
            store(file.outputStream(), null)
        }
    }

    fun getAppId() = current.get().getProperty("appId") ?: "cli"
}
