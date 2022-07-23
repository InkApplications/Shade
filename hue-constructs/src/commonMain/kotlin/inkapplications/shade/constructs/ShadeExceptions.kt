package inkapplications.shade.constructs

/**
 * Base/General class for all exceptions specific to shade.
 */
open class ShadeException(message: String? = null, cause: Throwable? = null): RuntimeException(message, cause)
