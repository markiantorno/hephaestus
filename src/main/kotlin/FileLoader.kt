import java.io.File

/**
 * All our processing is done on the Excel files that the community maintains. It is the 'source of truth' for the
 * specification.
 */
private const val excel2011Suffix = "xlsx";
private const val excel2003Suffix = "xml";
private const val excel2003XMLHeader = "<ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">\n"

fun findExcelFiles(directory: String): Sequence<File> {
    return walkDirectoryForExtension(directory, excel2011Suffix)
}

fun findSpreadsheetXMLFiles(directory: String): Sequence<File> {
    return walkDirectoryForExtension(directory, excel2003Suffix).filter(File::isSpreadsheetXML)
}

fun walkDirectoryForExtension(directory: String, suffix: String): Sequence<File> {
    // using extension function walk
    return File(directory)
        .walk()
        .filter { hasRequiredSuffix(it, suffix) }
}

fun hasRequiredSuffix(file: File, suffix: String): Boolean {
    return suffix == file.extension
}

fun File.isSpreadsheetXML(): Boolean {
    return this.readText().contains(excel2003XMLHeader)
}