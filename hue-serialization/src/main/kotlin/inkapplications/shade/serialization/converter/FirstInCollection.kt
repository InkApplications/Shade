package inkapplications.shade.serialization.converter

import com.squareup.moshi.Types
import inkapplications.shade.constructs.ShadeApiError
import inkapplications.shade.constructs.HueResult
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Pull the first success object out of the response.
 *
 * Hues sometimes delivers responses as arrays. For queries that only
 * have one reponse object in the response, this will convert the first
 * success into a response, provided it is not an error.
 */
@Target(AnnotationTarget.FUNCTION)
annotation class FirstInCollection

object FirstInCollectionConverterFactory: Converter.Factory() {
    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        if (annotations.none { it is FirstInCollection }) return null

        val rawType = Types.getRawType(type)
        val envelopeType = Types.newParameterizedType(HueResult::class.java, rawType)
        val listType = Types.newParameterizedType(List::class.java, envelopeType)
        val delegate = retrofit.nextResponseBodyConverter<List<HueResult<Any>>>(this, listType, annotations)

        return FirstInCollectionConverter(delegate)
    }
}

private class FirstInCollectionConverter<T>(private val delegate: Converter<ResponseBody, List<HueResult<T>>>): Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T? {
        val response = delegate.convert(value)
        val firstResponse = response?.firstOrNull()
        val error = firstResponse?.error
        if (error != null) throw ShadeApiError(error)
        return firstResponse?.success
    }
}
