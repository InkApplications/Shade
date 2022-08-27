package inkapplications.shade.structures

private val HUE_CA = """
    -----BEGIN CERTIFICATE-----
    MIICMjCCAdigAwIBAgIUO7FSLbaxikuXAljzVaurLXWmFw4wCgYIKoZIzj0EAwIw
    OTELMAkGA1UEBhMCTkwxFDASBgNVBAoMC1BoaWxpcHMgSHVlMRQwEgYDVQQDDAty
    b290LWJyaWRnZTAiGA8yMDE3MDEwMTAwMDAwMFoYDzIwMzgwMTE5MDMxNDA3WjA5
    MQswCQYDVQQGEwJOTDEUMBIGA1UECgwLUGhpbGlwcyBIdWUxFDASBgNVBAMMC3Jv
    b3QtYnJpZGdlMFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEjNw2tx2AplOf9x86
    aTdvEcL1FU65QDxziKvBpW9XXSIcibAeQiKxegpq8Exbr9v6LBnYbna2VcaK0G22
    jOKkTqOBuTCBtjAPBgNVHRMBAf8EBTADAQH/MA4GA1UdDwEB/wQEAwIBhjAdBgNV
    HQ4EFgQUZ2ONTFrDT6o8ItRnKfqWKnHFGmQwdAYDVR0jBG0wa4AUZ2ONTFrDT6o8
    ItRnKfqWKnHFGmShPaQ7MDkxCzAJBgNVBAYTAk5MMRQwEgYDVQQKDAtQaGlsaXBz
    IEh1ZTEUMBIGA1UEAwwLcm9vdC1icmlkZ2WCFDuxUi22sYpLlwJY81Wrqy11phcO
    MAoGCCqGSM49BAMCA0gAMEUCIEBYYEOsa07TH7E5MJnGw557lVkORgit2Rm1h3B2
    sFgDAiEA1Fj/C3AN5psFMjo0//mrQebo0eKd3aWRx+pQY08mk48=
    -----END CERTIFICATE-----
""".trimIndent()

/**
 * Defines a strategy for handling SSL communication with the hue bridge.
 */
open class SecurityStrategy private constructor() {

    /**
     * Connect to Hue over a completely insecure connection.
     */
    class Insecure(
        /**
         * The hostname to limit insecure traffic to.
         */
        val hostname: String,
    ): SecurityStrategy()

    /**
     * Use a default security scheme, letting the platform manage trust.
     */
    object PlatformTrust: SecurityStrategy()

    /**
     * Use a custom certificate by resolving a hostname to an IP address.
     */
    open class CustomCa(
        /**
         * The Root CA expected in the SSL chain
         */
        val certificatePem: String,

        /**
         * The hostname that traffic will be directed at.
         */
        val hostname: String,

        /**
         * The IP for which to resolve traffic directed at the [hostname]
         */
        val ip: String,
    ): SecurityStrategy()

    /**
     * Use Hue's CA when connecting to a device.
     */
    class HueCa(
        /**
         * IP of the device to connect to
         */
        ip: String,

        /**
         * ID of the device to be connected to.
         */
        deviceId: String
    ): CustomCa(
        certificatePem = HUE_CA,
        hostname = deviceId,
        ip = ip,
    )
}
