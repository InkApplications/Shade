package inkapplications.shade.serialization.converter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Convert Unit objects for Retrofit.
 */
object UnitConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        type == Unit::class.java || return null
        return Converter<ResponseBody, Unit> { value -> value.close() }
    }
}
