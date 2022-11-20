package inkapplications.shade.structures

/**
 * An event that was sent through the events api, but not deserialized by
 * any module.
 */
@UndocumentedApi
data class UnknownEvent(
    /**
     * Designated type for the data.
     */
    val type: String,

    /**
     * Raw JSON string of the event object.
     */
    val json: String,
)
