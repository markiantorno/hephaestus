package model

data class ParsedResource(
    val name: String,
    val resourceFields: List<ResourceField> = mutableListOf(),
    val bindingFields: List<BindingField> = mutableListOf()
)