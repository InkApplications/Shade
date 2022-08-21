package inkapplications.shade.internals

import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Caches a property value until a key changes.
 *
 * @param keyProperty Function that provides the latest value of the cache key.
 * @param lazy Whether to initialize the value immediately, or upon first invocation.
 * @param factory Function to create the property value based on its current cache key.
 */
class CachedProperty<KEY: Any, VALUE>(
    val keyProperty: () -> KEY,
    lazy: Boolean = false,
    val factory: (KEY) -> VALUE,
): ReadOnlyProperty<Any?, VALUE> {
    private sealed interface State<T> {
        class Null<T>: State<T>
        data class Set<T>(val key: Any, val value: T): State<T>
    }

    private val defaultState: State<VALUE> = if (lazy) State.Null() else create()
    private val cache: MutableStateFlow<State<VALUE>> = MutableStateFlow(defaultState)

    override fun getValue(thisRef: Any?, property: KProperty<*>): VALUE {
        return cache.value.let { currentState ->
            when {
                currentState is State.Set && currentState.key == keyProperty() -> currentState
                else -> create().also { cache.value = it }
            }
        }.value
    }

    private fun create(): State.Set<VALUE> = keyProperty().let { newKey ->
        State.Set(newKey, factory(newKey))
    }
}
