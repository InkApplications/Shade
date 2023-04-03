package inkapplications.shade.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

/**
 * Wrapper around responses from V1 endpoints in the Hue API
 */
@Serializable(with = V1HueResponse.Serializer::class)
abstract class V1HueResponse<out T> private constructor() {
    @Serializable
    data class Success<out T>(
        val success: T,
    ): V1HueResponse<T>()

    @Serializable
    data class Error(
        val error: V1HueError,
    ): V1HueResponse<Nothing>()

    @Serializable
    internal class PolymorphicResponse<T>(
        val success: T? = null,
        val error: V1HueError? = null,
    )

    internal class Serializer<T>(parent: KSerializer<T>): DelegateSerializer<PolymorphicResponse<T>, V1HueResponse<T>>(PolymorphicResponse.serializer(parent)) {
        override fun serialize(data: V1HueResponse<T>): PolymorphicResponse<T> {
            return when (data) {
                is Success -> PolymorphicResponse(
                    success = data.success,
                )
                is Error -> PolymorphicResponse(
                    error = data.error,
                )
                else -> throw IllegalArgumentException("Unhandled type: ${data::class.simpleName}")
            }
        }

        override fun deserialize(data: PolymorphicResponse<T>): V1HueResponse<T> {
            return data.success?.let { Success(it) }
                ?: data.error?.let { Error(it) }
                ?: throw IllegalArgumentException("Response with no success or error.")
        }
    }
}
