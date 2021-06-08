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
 * Custom parser to convert cardinality Strings to [Pair] of [CardinalityBound]
 */
class CardinalityCellParser : CellParser() {
    override fun parse(cell: Cell, obj: Any, field: Field) {
        try {
            setField(obj, field, cell.stringCellValue?.toBoundedPair() ?: Pair(CardinalityBound.UNSET, CardinalityBound.UNSET))
        } catch (e: IllegalArgumentException) {
            logFailure(cell, field, e)
        } catch (e: IllegalAccessException) {
            logFailure(cell, field, e)
        }
    }
}

/**
 * Extension function to a given cardinality String and transform it to an upper and lower bounds [Pair]].
 */
@Throws(IllegalArgumentException::class)
fun String.toBoundedPair(): Pair<CardinalityBound, CardinalityBound> {
    val delim = ".."
    if (!this.contains(delim)) throw IllegalArgumentException("Cardinality String, $this, doesn't contain delimiter characters \"..\".")
    val list = this.split(delim)
    if (list.size != 2) throw IllegalArgumentException("Cardinality String, $this, doesn't match required format \"<lower>..<upper>\".")
    return Pair(list.first().toBound(), list.last().toBound())
}

@Throws(IllegalArgumentException::class)
fun String.toBound(): CardinalityBound {
    return when(this) {
        "0" -> CardinalityBound.ALLOW_ZERO
        "1" -> CardinalityBound.ALLOW_ONE
        "*" -> CardinalityBound.ALLOW_MANY
        else -> throw IllegalArgumentException("Bad argument, $this, passed to toBound(), cannot parse.")
    }
}
