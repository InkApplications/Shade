package inkapplications.shade.auth.structures

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class AppIdSerializerTest {
    @Test
    fun serializer() {
        val appId = AppId(
            appName = "test-name",
            instanceName = "test-instance",
        )
        val json = """"test-name#test-instance"""".trimIndent()

        val encodedResult = Json.encodeToString(appId)
        val decodedResult = Json.decodeFromString<AppId>(json)

        assertEquals(json, encodedResult, "Encoded result")
        assertEquals(appId, decodedResult, "Decoding result")
    }
}
