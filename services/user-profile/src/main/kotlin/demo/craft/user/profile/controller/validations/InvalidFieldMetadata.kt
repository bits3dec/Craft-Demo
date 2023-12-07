package demo.craft.user.profile.controller.validations

data class InvalidFieldMetadata(
    val fieldName: String,
    val description: String
) {
    override fun toString(): String {
        return "FieldName: $fieldName, Description: $description"
    }
}
