package model.excel

data class ParsedSheet(
    val name: String,
    val rawResourceRows: List<RawResourceRow> = mutableListOf(),
    val rawBindingRows: List<RawBindingRow> = mutableListOf()
)