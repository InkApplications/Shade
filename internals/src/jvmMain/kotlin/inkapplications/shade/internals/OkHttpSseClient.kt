package inkapplications.shade.internals

import inkapplications.shade.structures.HostnameNotSetException
import inkapplications.shade.structures.HueConfigurationContainer
import kimchi.logger.KimchiLogger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources

internal class OkHttpSseClient(
    private val configurationContainer: HueConfigurationContainer,
    private val json: Json,
    okHttpClient: OkHttpClient,
    private val logger: KimchiLogger,
): SseClient {
    private val factory = EventSources.createFactory(okHttpClient)
    override fun <DATA> openSse(
        pathSegments: Array<out String>,
        dataDeserializer: DeserializationStrategy<DATA>,
    ): Flow<DATA> = callbackFlow {
        val host = configurationContainer.hostname.value ?: throw HostnameNotSetException
        val url = HttpUrl.Builder()
            .host(host)
            .scheme("https")
            .apply {
                pathSegments.forEach(::addPathSegment)
            }
            .build()
        val eventSource = factory.newEventSource(
            Request.Builder()
                .url(url)
                .header("Accept", "text/event-stream")
                .apply {
                    configurationContainer.authToken.value?.run {
                        logger.debug("Attaching Application key to request")
                        header("hue-application-key", applicationKey)
                    }
                }
                .get()
                .build(),
            object : EventSourceListener() {
                override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
                    super.onEvent(eventSource, id, type, data)
                    logger.trace("Got Event: $id ($type) with data: $data")

                    try {
                        val event = json.decodeFromString(dataDeserializer, data)
                        trySend(event)
                    } catch (e: Throwable) {
                        logger.error("Problem deserializing event", e)
                        e.printStackTrace()
                    }
                }

                override fun onClosed(eventSource: EventSource) {
                    logger.warn("Closed")
                }

                override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                    logger.error("Error ${t?.message}", cause = t)
                    logger.error("Response: ${response?.body?.string()}")
                }

                override fun onOpen(eventSource: EventSource, response: Response) {
                    super.onOpen(eventSource, response)
                    logger.trace("open")
                }
            }
        )

        awaitClose {
            eventSource.cancel()
        }
    }
}
