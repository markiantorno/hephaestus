package xmlconvertor

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.RichTextString
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook
import java.io.FileOutputStream
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.xmlgraphics.ps.dsc.events.DSCAtend
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

fun createWorkbook(data: Map<String, List<List<Any>>>): Workbook {
    val workbook: Workbook = XSSFWorkbook()
    for (sheetName in data.keys) {
        val currentSheet = workbook.createSheet(sheetName)
        data[sheetName]!!.forEachIndexed { rowIndex, rowData ->
            val currentRow = currentSheet.createRow(rowIndex)
            rowData.forEachIndexed { cellIndex, data ->
                val currentCell = currentRow.createCell(cellIndex)
                currentCell.setCellValue(data)
            }
        }
    }
    return workbook
}

private fun Cell.setCellValue(data: Any) {
    when (data) {
        is Date -> setCellValue(data)
        is Double -> setCellValue(data)
        is Boolean -> setCellValue(data)
        is String -> setCellValue(data)
        is Calendar -> setCellValue(data)
        is LocalDate -> setCellValue(data)
        is LocalDateTime -> setCellValue(data)
        is RichTextString -> setCellValue(data)
        else -> throw IllegalStateException("Cannot set cell value...")
    }
}

fun writeWorkbook(fileLocation: String, workbook: Workbook) {
    val outputStream = FileOutputStream(fileLocation)
    workbook.write(outputStream)
    workbook.close()
}
