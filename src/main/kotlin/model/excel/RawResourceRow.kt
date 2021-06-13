package model.excel

import annotation.CellInfo
import model.field.CardinalityBound
import model.excel.parser.BooleanStringCellParser
import model.excel.parser.CardinalityCellParser
import model.excel.parser.StringListCellParser

/**
 * The raw dump of all data in the xlsx resource file.
 */
data class RawResourceRow(
    @CellInfo(index = 0)
    val path: String = "",
    @CellInfo(index = 1, cellParser = StringListCellParser::class)
    val aliases: List<String> = mutableListOf(),
    @CellInfo(index = 2, cellParser = CardinalityCellParser::class)
    val cardinality: Pair<CardinalityBound, CardinalityBound> = Pair(CardinalityBound.UNSET, CardinalityBound.UNSET),
    @CellInfo(index = 3) val inv: String = "",
    @CellInfo(index = 4) val type: String = "",
    @CellInfo(index = 5) val hierarchy: String = "",
    @CellInfo(index = 6, cellParser = BooleanStringCellParser::class)
    val isModifier: Boolean = false,
    @CellInfo(index = 7) val modifierReason: String = "",
    @CellInfo(index = 8, cellParser = BooleanStringCellParser::class)
    val summary: Boolean = false,
    @CellInfo(index = 9) val binding: String = "",
    @CellInfo(index = 10) val example: String = "",
    @CellInfo(index = 11) val missingMeaning: String = "",
    @CellInfo(index = 12) val shortName: String = "",
    @CellInfo(index = 13) val definition: String = "",
    @CellInfo(index = 14) val requirements: String = "",
    @CellInfo(index = 15) val comments: String = "",
    @CellInfo(index = 16) val status: String = "",
    @CellInfo(index = 17) val uml: String = "",
    @CellInfo(index = 18) val displayHint: String = "",
    @CellInfo(index = 19) val committeeNotes: String = ""
)
