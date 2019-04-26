package inkapplications.shade.hueclient

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import retrofit2.Response

/**
 * Deserialize Error exceptions from responses.
 */
internal object ErrorParser {
    private val responseType = Types.newParameterizedType(HueResponse::class.java, Any::class.java)
    private val listType = Types.newParameterizedType(List::class.java, responseType)
    private val moshi = Moshi.Builder().build().adapter<List<HueResponse<Any>>>(listType)

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
