package inkapplications.shade.internals

import inkapplications.shade.structures.HueConfigurationContainer
import inkapplications.shade.structures.SecurityStrategy
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import kimchi.logger.KimchiLogger
import kotlinx.serialization.json.Json
import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.tls.HandshakeCertificates
import okhttp3.tls.decodeCertificatePem
import java.net.InetAddress
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

actual class PlatformModule actual constructor(
    private val configurationContainer: HueConfigurationContainer,
    private val json: Json,
    private val logger: KimchiLogger
) {
    val sseClient by CachedProperty(configurationContainer.securityStrategy::value) { key ->
        createSseClient(key)
    }

    actual fun createEngine(securityStrategy: SecurityStrategy): HttpClientEngineFactory<*> {
        return object: HttpClientEngineFactory<OkHttpConfig> {
            override fun create(block: OkHttpConfig.() -> Unit): HttpClientEngine = OkHttpEngine(OkHttpConfig().apply {
                config { applyPlatformConfiguration(this, securityStrategy) }
            }.apply(block))
        }
    }

    fun applyPlatformConfiguration(builder: OkHttpClient.Builder, securityStrategy: SecurityStrategy) = builder.apply {
        when (securityStrategy) {
            is SecurityStrategy.Insecure -> insecure(securityStrategy)
            is SecurityStrategy.CustomCa -> withSecurity(securityStrategy)
            is SecurityStrategy.PlatformTrust -> {}
            else -> throw IllegalArgumentException("Unsupported Security Scheme: ${securityStrategy::class.simpleName}")
        }
    }

    private fun OkHttpClient.Builder.insecure(strategy: SecurityStrategy.Insecure) {
        val certificates = HandshakeCertificates.Builder()
            .addInsecureHost(strategy.hostname)
            .build()
        hostnameVerifier { hostname, session -> hostname == strategy.hostname }

        val trustManager = object: X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return certificates.trustManager.acceptedIssuers
            }
        }
        val socketFactory = Platform.get().newSSLContext().apply {
            init(arrayOf(certificates.keyManager), arrayOf<TrustManager>(trustManager), SecureRandom())
        }.socketFactory

        sslSocketFactory(socketFactory, trustManager)
    }

    private fun OkHttpClient.Builder.withSecurity(strategy: SecurityStrategy.CustomCa) {
        val certificates = HandshakeCertificates.Builder()
            .addTrustedCertificate(strategy.certificatePem.decodeCertificatePem())
            .build()
        sslSocketFactory(certificates.sslSocketFactory(), certificates.trustManager)
        dns(object: Dns {
            override fun lookup(hostname: String): List<InetAddress> {
                if (hostname == strategy.hostname) {
                    return listOf(InetAddress.getByName(strategy.ip))
                }
                return emptyList()
            }
        })
        hostnameVerifier { hostname, session -> hostname == strategy.hostname }
    }

    private fun createSseClient(securityStrategy: SecurityStrategy): SseClient {
        val okHttpClient = OkHttpClient.Builder().apply {
            applyPlatformConfiguration(this, securityStrategy)
            readTimeout(0, TimeUnit.SECONDS)
        }.build()
        return OkHttpSseClient(configurationContainer, json, okHttpClient, logger)
    }
}

