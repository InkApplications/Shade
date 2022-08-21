package inkapplications.shade.internals

import kotlin.test.Test
import kotlin.test.assertEquals

class CachedPropertyTest {
    @Test
    fun lazy() {
        var invocations = 0
        val property: Unit by CachedProperty({ "test" }, lazy = true) {
            invocations++
        }

        assertEquals(0, invocations, "Lazy construct expects no factory calls before get.")

        property

        assertEquals(1, invocations, "Lazy construct calls factory upon first call")
    }

    @Test
    fun eager() {
        var invocations = 0
        val property: Unit by CachedProperty({ "test" }, lazy = false) {
            invocations++
        }

        assertEquals(1, invocations, "Eager construct expects one factory call on initialization.")
    }

    @Test
    fun cache() {
        var invocations = 0
        val property: Unit by CachedProperty({ "test" }) {
            invocations++
        }

        property
        property
        assertEquals(1, invocations, "Multiple calls invoke factory only once")
    }

    @Test
    fun cacheChange() {
        var key = "test"
        var invocations = 0
        val property: Unit by CachedProperty({ key }) {
            invocations++
        }

        property
        property
        assertEquals(1, invocations, "Factory not invoked before key change")

        key = "test-2"
        property
        property
        assertEquals(2, invocations, "Factory invoked an additional time after key change")
    }

}
