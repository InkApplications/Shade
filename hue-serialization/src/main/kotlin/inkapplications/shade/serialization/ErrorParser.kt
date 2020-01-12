package inkapplications.shade.serialization

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import inkapplications.shade.constructs.HueResult
import inkapplications.shade.constructs.ShadeException
import inkapplications.shade.constructs.UnknownException
import inkapplications.shade.constructs.asException
import retrofit2.HttpException
import retrofit2.Response

/**
 * Deserialize Error exceptions from responses.
 */
object ErrorParser {
    private val responseType = Types.newParameterizedType(HueResult::class.java, Any::class.java)
    private val listType = Types.newParameterizedType(List::class.java, responseType)
    private val moshi = Moshi.Builder().build().adapter<List<HueResult<Any>>>(listType)

    fun parseError(response: Response<*>): ShadeException {
        response.errorBody().use { body ->
            if (body == null) return ShadeException("API returned Error with no content")
            return try {
                moshi.fromJson(body.string())
                    ?.map { it.error }
                    ?.filterNotNull()
                    ?.asException()
                    ?: ShadeException("API Returned Error with no specifics.")
            } catch (error: Throwable) {
                return ShadeException("Problem deserializing error", error)
            }
        }
    }
}

/**
 * Get a parsed exception from an HTTP Exception.
 */
fun HttpException.parse(): Throwable = throw ErrorParser.parseError(response() ?: throw UnknownException(this))

/**
 * Run a block of code, catching any internal errors.
 */
inline fun <T> encapsulateErrors(block: () -> T): T {
    try {
        return block()
    } catch (httpError: HttpException) {
        throw httpError.parse()
    }
}
