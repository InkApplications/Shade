package inkapplications.shade.delegates

import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic
import java.lang.IllegalStateException

/**
 * Holds a reference to an endpoint that can be recreated.
 *
 * If the endpoint is used before a baseurl is set, this will
 * throw an [IllegalArgumentException].
 *
 * @param initialUrl The baseurl to start with (optional)
 */
internal abstract class EndpointDelegate<T>(private val initialUrl: String?) {
    private val delegateReference: AtomicRef<T?> = atomic(null)
    protected val delegate: T get() = delegateReference.value
        ?: initialUrl?.let(::createEndpoint)
        ?: throw IllegalStateException("BaseURL of Hue bridge was not set. It can be set when Shade is constructed or by calling `Shade#setBaseUrl`")

    /**
     * Create a new instance of the endpoint.
     *
     * @param baseUrl The baseurl to use when creating the endpoint.
     * @return The new Endpoint configuration.
     */
    abstract fun createEndpoint(baseUrl: String): T

    /**
     * Re-create the endpoint with the specified baseurl.
     *
     * @param url the base url to use for the new endpoint.
     */
    fun setUrl(url: String) {
        delegateReference.value = createEndpoint(url)
    }
}
