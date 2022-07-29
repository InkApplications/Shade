package inkapplications.shade.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

/**
 * Wrapper around responses from the Hue API
 */
@Serializable(with = HueResponse.Serializer::class)
abstract class HueResponse<T> private constructor() {
    /**
     * Response that successfully retrieved data.
     */
    data class Success<T>(
        val errors: List<HueError>,
        /**
         * Requested data
         */
        val data: T,
    ): HueResponse<T>()

    /**
     * Response with no data, and potentially errors.
     */
    data class Error(
        val errors: List<HueError>,
    ): HueResponse<Nothing>()

    /**
     * Polymorphic response object used only for serialization.
     */
    @Serializable
    internal data class PolymorphicResponse<T>(
        val errors: List<HueError>,
        val data: T? = null,
    )

    internal class Serializer<T>(parent: KSerializer<T>): DelegateSerializer<PolymorphicResponse<T>, HueResponse<out T>>(PolymorphicResponse.serializer(parent)) {
        override fun serialize(data: HueResponse<out T>): PolymorphicResponse<T> {
            return when (data) {
                is Success -> PolymorphicResponse(
                    errors = data.errors,
                    data = data.data,
                )
                is Error -> PolymorphicResponse(
                    errors = data.errors,
                )
                else -> throw IllegalArgumentException("Unhandled type: ${data::class.simpleName}")
            }
        }

        override fun deserialize(data: PolymorphicResponse<T>): HueResponse<out T> {
            return when {
                data.data != null -> HueResponse.Success(
                    data = data.data,
                    errors = data.errors,
                )
                else -> Error(
                    errors = data.errors,
                )
            }
        }
    }
}

