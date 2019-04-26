package inkapplications.shade.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import inkapplications.shade.hueclient.FirstInCollection
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Hue Bridge Authentication endpoints.
 */
internal interface HueAuthApi {
    /**
     * Request an auth token.
     *
     * This is a bizarre endpoint. It will succeed with a 200, but with
     * a code 101 error in the body indicating that the button has not
     * been pressed. If you keep hitting this endpoint over and over
     * it will receive a token *ONLY ONCE* the next time after the
     * button has been pressed.
     * It's a good idea to put a timeout and delay inbetween retries.
     *
     * @param devicetype Object used to identify the app.
     * @return Hue calls this a username, but it is definitely a bearer
     *         token that is needed for all authenticated requests.
     */
    @POST("api/")
    @FirstInCollection
    fun createToken(@Body devicetype: DeviceType): Deferred<AuthToken>
}

/**
 * Identifier for the App used in authentication.
 *
 * @property appId A string used to identify the app between requests.
 */
@JsonClass(generateAdapter = true)
internal data class DeviceType(
    @Json(name = "devicetype") val appId: String
)

/**
 * A Token from the Hue API.
 *
 * @property token An auth token for making calls to the API.
 *           ABSOLUTELY not a username holy shit.
 */
@JsonClass(generateAdapter = true)
data class AuthToken(
    @Json(name = "username") val token: String
)
