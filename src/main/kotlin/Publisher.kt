import model.excel.ParsedSheet
import xmlconvertor.createWorkbook
import xmlconvertor.readExcel
import xmlconvertor.writeWorkbook

const val absPathToExcel: String = "/home/mark/Documents/Projects/FHIR/fhir/source"
//const val absPathToExcel: String = "/home/mark/Documents/Projects/hephaestus/src/main/resources"

val mappedBindings = HashMap<String, ParsedSheet>()

fun main(args: Array<String>) {
    println("Starting parsing files...")
    val excelFiles = findSpreadsheetXMLFiles(absPathToExcel)
    excelFiles.forEach { println(it.name) }
    excelFiles.forEach { file ->
        val readExcel = readExcel(file.inputStream())
        writeWorkbook(
            "/home/mark/Documents/Projects/hephaestus/src/main/resources/output.xlsx",
            createWorkbook(readExcel)
        )
        println()
    }

//    val excelFiles = findExcelFiles(absPathToExcel)
//
//    excelFiles.forEach { file ->
//        var resourceFields = readSheet(file.absolutePath, SheetDescriptor(RawResourceRow::class.java).setHasHeader(true))
//        var bindingFields = readSheet(file.absolutePath, SheetDescriptor(RawBindingRow::class.java).setSheetIndex(1).setHasHeader(true))
//        mappedBindings[file.name] = ParsedSheet(
//            name = file.name.substringBefore('.'),
//            rawResourceRows = readSheet(file.absolutePath, SheetDescriptor(RawResourceRow::class.java).setHasHeader(true)),
//            rawBindingRows = readSheet(file.absolutePath, SheetDescriptor(RawBindingRow::class.java).setSheetIndex(1).setHasHeader(true))
//        )
//    }
//    println("Parsing complete, dumping...")
    // mappedBindings["account-spreadsheet.xlsx"]?.rawResourceRows?.forEach { println(it) }
    // mappedBindings["account-spreadsheet.xlsx"]?.rawBindingRows?.forEach { println(it) }
}
