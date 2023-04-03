package inkapplications.shade.structures

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.io.File
import java.util.*

/**
 * Load and save Hue configuration and authentication to a file.
 *
 * # File Format
 * ## Keys
 * To assign a known application key in your file specify the `application_key`
 * and optionally `client_key` properties:
 *
 *     application_key=abcd1234
 *     client_key=defg5678
 *
 * ## Hostname
 * To configure the Hue bridge's hostname, specify the `hostname` parameter.
 * This is typically an IP address, but can be any valid hostname.
 * Do not include the protocol (`https://`) as this is configured by the
 * security strategy.
 *
 *     hostname=192.168.1.5
 *
 * ## Security Strategy
 * To configure a security strategy, specify the `security` parameter, and
 * its associated parameters for that type. The available types are:
 *
 * ### Platform Security
 * This is the default security type, but can also be explicitly specified
 * in the properties file, and requires no extra parameters:
 *
 *     security=platform
 *
 * ### Insecure
 * To use an insecure connection, specify the security as `insecure` and
 * the `hostname` of the hue bridge will be allow-listed for insecure http
 * connections:
 *
 *     hostname=192.168.1.5
 *     security=insecure
 *
 * ### Hue CA
 * To use Hue's Certificate Authority for TLS security, specify the security
 * as `hue`. Hue's security uses the Device ID as the `hostname` parameter, and
 * requires an `ip` to resolve that hostname to:
 *
 *     hostname=01234xxabcdef
 *     security=hue
 *     ip=192.168.1.5
 *
 * ### Custom CA
 * To use your own custom CA configuration, specify the security as `custom`
 * The CA file can be specified with the `certificate_pem_file`, along with the
 * `hostname` and `ip` to resolve the host to.
 *
 * Note that Custom CA's do not support modifying the certificate data from the
 * SDK.
 *
 *     hostname=myhue.localdomain
 *     security=custom
 *     ip=192.168.1.5
 *     certificate_pem_file=/var/shade/custom_cert.pem
 *
 * @param file The properties file to load or save data from.
 */
class PropertiesFileConfiguration(
    private val file: File = File(
        System.getProperty("user.home")
            .takeIf { it.isNullOrEmpty() == false },
        ".shade-cli.properties"
    ),
): HueConfigurationContainer {
    private object PropertyKeys {
        const val SECURITY = "security"
        const val HOSTNAME = "hostname"
        const val CERTIFICATE_PEM_FILE = "certificate_pem_file"
        const val IP = "ip"
        const val APPLICATION_KEY = "application_key"
        const val CLIENT_KEY = "client_key"
    }

    private object SecurityKeys {
        const val PLATFORM = "platform"
        const val INSECURE = "insecure"
        const val CUSTOM = "custom"
        const val HUE = "hue"
    }

    private val properties: Properties by lazy {
        Properties()
            .apply {
                if (file.exists()) file.inputStream().run(::load)
            }
    }

    private val mutableHostname: MutableStateFlow<String?> by lazy {
        MutableStateFlow(properties.getProperty(PropertyKeys.HOSTNAME))
    }

    override val hostname: StateFlow<String?> = mutableHostname

    private val mutableSecurityStrategy: MutableStateFlow<SecurityStrategy> by lazy {
        val security = properties.getProperty(PropertyKeys.SECURITY, SecurityKeys.PLATFORM)
        val initialStrategy = when (security) {
            SecurityKeys.CUSTOM -> SecurityStrategy.CustomCa(
                certificatePem = properties.getProperty(PropertyKeys.CERTIFICATE_PEM_FILE)
                    ?.let { File(it) }
                    ?.readText()
                    ?: throw InvalidConfigurationException("certificate_pem_file field must be specified in config"),
                hostname = properties.getProperty(PropertyKeys.HOSTNAME)
                    ?: throw InvalidConfigurationException("hostname field must be specified in config"),
                ip = properties.getProperty(PropertyKeys.IP)
                    ?: throw InvalidConfigurationException("ip field must be specified in config"),
            )
            SecurityKeys.HUE -> SecurityStrategy.HueCa(
                deviceId = properties.getProperty(PropertyKeys.HOSTNAME)
                    ?: throw InvalidConfigurationException("device_id field must be specified in config"),
                ip = properties.getProperty(PropertyKeys.IP)
                    ?: throw InvalidConfigurationException("ip field must be specified in config"),
            )
            SecurityKeys.INSECURE -> SecurityStrategy.Insecure(properties.getProperty(PropertyKeys.HOSTNAME)
                ?: throw InvalidConfigurationException("hostname field must be specified in config"))
            SecurityKeys.PLATFORM, null -> SecurityStrategy.PlatformTrust
            else -> throw IllegalArgumentException("Unknown security type in config: $security")
        }

        MutableStateFlow(initialStrategy)
    }
    override val securityStrategy: StateFlow<SecurityStrategy> = mutableSecurityStrategy

    private val mutableAuthToken: MutableStateFlow<AuthToken?> by lazy {
        val applicationKey = properties.getProperty(PropertyKeys.APPLICATION_KEY)
        val clientKey = properties.getProperty(PropertyKeys.CLIENT_KEY)
        val authToken = applicationKey?.let { AuthToken(it, clientKey) }

        MutableStateFlow(authToken)
    }
    override val authToken: StateFlow<AuthToken?> = mutableAuthToken

    override suspend fun setHostname(hostname: String?) {
        withContext(Dispatchers.IO) {
            properties.setProperty(PropertyKeys.HOSTNAME, hostname)
            properties.store(file.outputStream(), null)
        }
        mutableHostname.value = hostname
    }

    override suspend fun setAuthToken(key: AuthToken?) {
        withContext(Dispatchers.IO) {
            properties.setProperty(PropertyKeys.APPLICATION_KEY, key?.applicationKey)
            properties.setProperty(PropertyKeys.CLIENT_KEY, key?.clientKey)
            properties.store(file.outputStream(), null)
        }
        mutableAuthToken.value = key
    }

    override suspend fun setSecurityStrategy(securityStrategy: SecurityStrategy) {
        withContext(Dispatchers.IO) {
            when (securityStrategy) {
                is SecurityStrategy.PlatformTrust -> {
                    properties.setProperty(PropertyKeys.SECURITY, SecurityKeys.PLATFORM)
                }
                is SecurityStrategy.Insecure -> {
                    properties.setProperty(PropertyKeys.SECURITY, SecurityKeys.INSECURE)
                    properties.setProperty(PropertyKeys.HOSTNAME, securityStrategy.hostname)
                }
                is SecurityStrategy.HueCa -> {
                    properties.setProperty(PropertyKeys.SECURITY, SecurityKeys.HUE)
                    properties.setProperty(PropertyKeys.IP, securityStrategy.ip)
                    properties.setProperty(PropertyKeys.HOSTNAME, securityStrategy.hostname)
                }
                is SecurityStrategy.CustomCa -> {
                    properties.setProperty(PropertyKeys.SECURITY, SecurityKeys.CUSTOM)
                    properties.setProperty(PropertyKeys.HOSTNAME, securityStrategy.hostname)
                    properties.setProperty(PropertyKeys.IP, securityStrategy.ip)
                    if (securityStrategy.certificatePem != (this@PropertiesFileConfiguration.securityStrategy.value as? SecurityStrategy.CustomCa)?.certificatePem) {
                        throw UnsupportedOperationException("Cannot change Certificate PEM specified in config file")
                    }
                }
            }
            properties.store(file.outputStream(), null)
        }
        mutableSecurityStrategy.value = securityStrategy
    }
}
