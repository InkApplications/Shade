package inkapplications.shade.cli.scenes

/**
 * Assert that two lists are the same size.
 */
fun assertSameSize(expected: List<*>?, actual: List<*>?, message: String) {
    if (expected?.size != actual?.size) {
        throw IllegalArgumentException(message)
    }
}
