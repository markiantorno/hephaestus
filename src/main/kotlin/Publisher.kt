import model.BindingField
import model.ParsedResource
import model.ResourceField
import util.readSheet

const val absPathToExcel: String = "/home/mark/Documents/Projects/FHIR/fhir/source"
val mappedBindings = HashMap<String, ParsedResource>()

fun main(args: Array<String>) {
    println("Starting parsing files...")
    val excelFiles = findExcelFiles(absPathToExcel)
    excelFiles.forEach { file ->
        mappedBindings[file.name] = ParsedResource(
            name = file.name.substringBefore('.'),
            resourceFields = readSheet(file.absolutePath, SheetDescriptor(ResourceField::class.java).setHasHeader(true)),
            bindingFields = readSheet(file.absolutePath, SheetDescriptor(BindingField::class.java).setSheetIndex(1).setHasHeader(true))
        )
    }
    println("Parsing complete, dumping...")
    mappedBindings["account-spreadsheet.xlsx"]?.resourceFields?.forEach { println(it) }
    mappedBindings["account-spreadsheet.xlsx"]?.bindingFields?.forEach { println(it) }

}
