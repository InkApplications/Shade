package shade.http

import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.Semaphore

/**
 * Header key for the time to delay a request by, in milliseconds.
 */
const val RATE_LIMIT = "X-RateLimit"

/**
 * Limits the requests on a client via a [RATE_LIMIT] header.
 *
 * This blocks the response of a request by the rate limit specified in the
 * [RATE_LIMIT] header so that requests do not exceed Hue's throughput.
 */
object RateLimitInterceptor: Interceptor {
    private val semaphore = Semaphore(1, true)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val limit = request.header(RATE_LIMIT)?.toLong() ?: 0

        semaphore.acquire()
        val response = request.newBuilder()
            .removeHeader(RATE_LIMIT)
            .build()
            .run(chain::proceed)
        Thread.sleep(limit)
        semaphore.release()

        return response
    }
}

