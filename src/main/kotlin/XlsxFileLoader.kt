import java.io.File

/**
 * All our processing is done on the Excel files that the community maintains. It is the 'source of truth' for the
 * specification.
 */
private val requiredSuffixes = listOf("xlsx")

fun findExcelFiles(directory: String): Sequence<File> {
    // using extension function walk
    return File(directory)
        .walk()
        .filter { hasRequiredSuffix(it) }
}

fun hasRequiredSuffix(file: File): Boolean {
    return requiredSuffixes.contains(file.extension)
}