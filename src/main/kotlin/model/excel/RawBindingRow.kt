package model.excel

import annotation.CellInfo

data class RawBindingRow(
    @CellInfo(index = 0) val bindingName: String = "",
    @CellInfo(index = 1) val definition: String = "",
    @CellInfo(index = 2) val strength: String = "",
    @CellInfo(index = 3) val valueSet: String = "",
    @CellInfo(index = 4) val description: String = "",
    @CellInfo(index = 5) val oid: String = "",
    @CellInfo(index = 6) val uri: String = "",
    @CellInfo(index = 7) val websiteEmail: String = "",
    @CellInfo(index = 8) val v2: String = "",
    @CellInfo(index = 9) val v3: String = "",
    @CellInfo(index = 10) val copyright: String = "",
    @CellInfo(index = 11) val committeeNotes: String = "",
)
