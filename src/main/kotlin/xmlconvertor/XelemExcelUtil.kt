package xmlconvertor

import nl.fountain.xelem.excel.Workbook
import nl.fountain.xelem.lex.ExcelReader
import org.xml.sax.InputSource
import java.io.InputStream
import java.util.*

/**
 * Read spreadsheetml type Excel
 *
 * @param in
 * @return
 * @throws IllegalArgumentException
 */
@Throws(IllegalArgumentException::class)
fun readExcel(inputStream: InputStream?): Map<String, List<List<Any>>> {
    val excelReader = ExcelReader()
    val inputSource = InputSource(inputStream)
    val book: Workbook? = excelReader.getWorkbook(inputSource)
    book?.let { workbook ->
        val workbookData: MutableMap<String, List<List<Any>>> = HashMap()
        workbook.sheetNames.forEach { sheetName ->
            val sheetData: MutableList<List<Any>> = ArrayList()
            workbook.getWorksheet(sheetName).rows.forEach { row ->
                val rowData: MutableList<Any> = ArrayList()
                row.cells.forEach { cell ->
                    rowData.add(cell.data)
                }
                sheetData.add(rowData)
            }
            workbookData[sheetName.mapSheetName()] = sheetData
        }
        return workbookData
    } ?: throw IllegalArgumentException("Bad input file.")
}

fun String.mapSheetName(): String {
    return when(this) {
        // Data types have no 'Resource' tab, they instead have a 'Data Elements' tab
        "Data Elements" -> "Resource"
        else -> this
    }
}