package inkapplications.shade.structures

/**
 * Annotation used to designate APIs in the hue system that were not documented.
 *
 * Since these methods don't have documentation in Hue's official docs, the
 * data types and methods may change at any time, including on the Bridge
 * itself, meaning the code may break over time. Data structures are filled in
 * by interpreting responses and are likely incomplete and may be incorrect
 * or incomplete for different devices.
 * Changes made to methods with this annotation will NOT be considered a
 * breaking change between versions of the Shade SDK.
 *
 * Use with caution.
 */
@RequiresOptIn(
    message = "This uses an undocumented Hue API and may be unreliable, incorrect, and is subject to change at any time."
)
@Retention(AnnotationRetention.BINARY)
annotation class UndocumentedApi
