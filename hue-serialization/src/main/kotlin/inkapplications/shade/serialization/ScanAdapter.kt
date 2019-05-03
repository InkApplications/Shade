package inkapplications.shade.serialization

import com.squareup.moshi.*
import inkapplications.shade.constructs.Scan
import inkapplications.shade.constructs.ScanReference
import org.threeten.bp.Instant
import java.lang.reflect.Type

/**
 * Just wraps Hue's bizarre name object.
 */
@JsonClass(generateAdapter = true)
internal data class NameToken(val name: String)

/**
 * Converts Hue's polymorphic-by-key scan results for lights and sensors.
 *
 * This data horrendous data structure is really difficult to
 * deserialize because it is polymorphic as completely disparate types
 * that must be discriminated by the key.
 * Also if there wasn't a last scan, the API returns the string "none"
 * instead of a null value. What the hell? why?
 * Luckily, these only seem to appear in the "find new" device API's.
 */
object ScanAdapter: JsonAdapter.Factory {
    private val LAST_SCAN_KEY = "lastscan"

    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        if (Types.getRawType(type) != Scan::class.java) return null

        val nameAdapter: JsonAdapter<NameToken> = moshi.adapter(NameToken::class.java)

        return object: JsonAdapter<Scan>() {
            override fun fromJson(reader: JsonReader): Scan? {
                reader.beginObject()
                var lastScan: Instant? = null
                val lights = mutableListOf<ScanReference>()

                while(reader.hasNext()) {
                    val key = reader.nextName()
                    when (key) {
                        LAST_SCAN_KEY -> reader.nextString()
                            .let { if (it == "none") null else InstantDeserializer.fromJson(it) }
                            ?.also { lastScan = it }
                        else -> reader.peekJson()
                            .use { nameAdapter.fromJson(it) }
                            ?.let { ScanReference(key, it.name) }
                            ?.also { lights.add(it) }
                            ?.also { reader.skipValue() }
                    }
                }
                reader.endObject()

                return Scan(
                    lastScan = lastScan,
                    lights = lights
                )
            }

            override fun toJson(writer: JsonWriter, value: Scan?) = throw NotImplementedError("This object is not sent to the API")
        }
    }
}
