package inkapplications.shade.serialization.adapter

import inkapplications.shade.serialization.ErrorParser
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.*
import java.lang.IllegalStateException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * A Call Adapter for Deferred that handles API error bodies.
 */
object ShadeDeferredCallAdapterFactory: CallAdapter.Factory() {
    override fun get(returnType: Type, nnotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (Deferred::class.java != getRawType(returnType)) return null
        if (returnType !is ParameterizedType) throw IllegalStateException("Deferred return type must be parameterized as Deferred<Foo> or Deferred<out Foo>")
        val responseType = getParameterUpperBound(0, returnType)

        return object : CallAdapter<Any, Deferred<*>> {

            override fun responseType() = responseType

            override fun adapt(call: Call<Any>): Deferred<*> {
                val deferred = CompletableDeferred<Any>()

                deferred.invokeOnCompletion {
                    if (deferred.isCancelled) {
                        call.cancel()
                    }
                }

                call.enqueue(object : Callback<Any> {
                    override fun onFailure(call: Call<Any>, t: Throwable) {
                        deferred.completeExceptionally(t)
                    }

                    override fun onResponse(call: Call<Any>, response: Response<Any>) {
                        if (response.isSuccessful) {
                            deferred.complete(response.body()!!)
                        } else {
                            deferred.completeExceptionally(ErrorParser.parseError(response))
                        }
                    }
                })

                return deferred
            }
        }
    }
}
