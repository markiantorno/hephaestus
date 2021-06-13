package model.excel.parser

import org.apache.poi.ss.usermodel.Cell
import parser.CellParser
import util.setField
import java.lang.reflect.Field

/**
 * Custom parser to convert a cell String value to [Boolean]
 */
class BooleanStringCellParser : CellParser() {
    override fun parse(cell: Cell, obj: Any, field: Field) {
        try {
            setField(obj, field, cell.stringCellValue?.toBoolean() ?: false)
        } catch (e: IllegalAccessException) {
            logFailure(cell, field, e)
        }
    }
}
