package model.parser

import model.field.CardinalityBound
import org.apache.poi.ss.usermodel.Cell
import parser.CellParser
import util.setField
import java.lang.reflect.Field
import kotlin.jvm.Throws
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Custom parser to convert a cell String value to a list of strings.
 */
class StringListCellParser : CellParser() {
    override fun parse(cell: Cell, obj: Any, field: Field) {
        try {
            setField(obj, field, cell.stringCellValue?.split(',') ?: mutableListOf<String>())
        } catch (e: NumberFormatException) {
            logFailure(cell, field, e)
        } catch (e: IllegalAccessException) {
            logFailure(cell, field, e)
        }
    }
}
