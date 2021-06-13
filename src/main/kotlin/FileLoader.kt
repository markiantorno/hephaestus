import java.io.File

/**
 * All our processing is done on the Excel files that the community maintains. It is the 'source of truth' for the
 * specification.
 */
private const val excel2011Suffix = "xlsx";
private const val excel2003Suffix = "xml";
private val excel2003XMLHeader: CharSequence = "xmlns=\"urn:schemas-microsoft-com:office:office\""//"<ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">\n"
private const val extensionSpreadsheetTitle = "-extensions"
private const val profileSpreadsheetTitle = "-profile"
private const val spreadsheetTitle = "-spreadsheet"
private const val profilesPath = "/profiles/"
private const val sourceParentDirectory = "source"

fun findExcelFiles(directory: String): Sequence<File> {
    return walkDirectoryForExtension(directory, excel2011Suffix)
}

fun findSpreadsheetXMLFiles(directory: String): Sequence<File> {
    return walkDirectoryForExtension(directory, excel2003Suffix)
        .filter(File::isSpreadsheetXML)
        .filterNot(File::isExtensionSpreadsheet)
        .filterNot(File::isProfileSpreadsheet)
        .filterNot(File::isDataSpreadsheet)
        .filterNot(File::isInProfilesDirectory)
        .filterNot(File::isInBaseDirectory)
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
    return this.readText().contains(excel2003XMLHeader, false)
}

fun File.isExtensionSpreadsheet(): Boolean {
    return this.name.contains(extensionSpreadsheetTitle, false)
}

fun File.isProfileSpreadsheet(): Boolean {
    return this.name.contains(profileSpreadsheetTitle, false)
}

fun File.isDataSpreadsheet(): Boolean {
    return this.name.contains(spreadsheetTitle, false)
}

fun File.isInProfilesDirectory(): Boolean {
    return this.absolutePath.contains(profilesPath, false)
}

fun File.isInBaseDirectory(): Boolean {
    return this.parentFile.name == "source"
}