package demo.craft.user.profile.controller.validations

import demo.craft.common.domain.enums.TaxType
import demo.craft.common.domain.kafka.impl.AddressMessage
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.user.profile.controller.validations.enums.AddressType
import demo.craft.user.profile.controller.validations.enums.UserProfileFieldName
import javax.transaction.NotSupportedException

    fun UserProfileMessage.validateFields(): List<InvalidFieldMetadata> {
        val invalidFields = mutableListOf<InvalidFieldMetadata>()

        when {
            this.companyName.isEmpty() -> invalidFields.add(
                InvalidFieldMetadata(
                    UserProfileFieldName.COMPANY_NAME.name,
                    "Value: $companyName should not be empty"
                )
            )

            this.legalName.isEmpty() -> invalidFields.add(
                InvalidFieldMetadata(
                    UserProfileFieldName.LEGAL_NAME.name,
                    "Value: $legalName should not be empty"
                )
            )

            this.taxIdentifier.value.isEmpty() -> {
                val invalidField =
                    when (this.taxIdentifier.type) {
                        TaxType.PAN -> InvalidFieldMetadata(UserProfileFieldName.PAN.name, "Value: ${this.taxIdentifier.value} should not be empty")
                        TaxType.EIN -> InvalidFieldMetadata(UserProfileFieldName.EIN.name, "Value: ${this.taxIdentifier.value} should not be empty")
                        else -> throw NotSupportedException("${this.taxIdentifier.type} is not a valid tax type")
                    }
                invalidFields.add(invalidField)
            }

            this.taxIdentifier.value.isNotEmpty() -> {
                if (this.taxIdentifier.type == TaxType.PAN) {
                    if (!isPanValid(taxIdentifier.value)) {
                        invalidFields.add(
                            InvalidFieldMetadata(UserProfileFieldName.PAN.name, "Value:${taxIdentifier.value} is not valid")
                        )
                    }
                } else {
                    if (!isEinValid(taxIdentifier.value)) {
                        invalidFields.add(
                            InvalidFieldMetadata(UserProfileFieldName.EIN.name, "Value: ${taxIdentifier.value} is not valid")
                        )
                    }
                }
            }

            this.email.isEmpty() -> invalidFields.add(InvalidFieldMetadata(UserProfileFieldName.EMAIL.name, "Value: $email should not be empty"))
            this.website.isEmpty() -> invalidFields.add(InvalidFieldMetadata(UserProfileFieldName.WEBSITE.name, "Value: $website should not be empty"))
        }

        invalidFields.addAll(businessAddress.validateFields(AddressType.BUSINESS_ADDRESS))
        invalidFields.addAll(legalAddress.validateFields(AddressType.LEGAL_ADDRESS))

        return invalidFields
    }

    private fun AddressMessage.validateFields(addressType: AddressType): List<InvalidFieldMetadata> {
        val invalidFields = mutableListOf<InvalidFieldMetadata>()

        if (this.line1.isEmpty()) {
            invalidFields.add(
                InvalidFieldMetadata(addressType.name + SEPARATOR + UserProfileFieldName.LINE_1.name, "Value: $line1 should not be empty")
            )
        }

        if (this.line2.isEmpty()) {
            invalidFields.add(
                InvalidFieldMetadata(addressType.name + SEPARATOR + UserProfileFieldName.LINE_2.name, "Value: $line2 should not be empty")
            )
        }

        if (this.city.isEmpty()) {
            invalidFields.add(
                InvalidFieldMetadata(addressType.name + SEPARATOR + UserProfileFieldName.CITY.name, "Value: $city should not be empty")
            )
        }

        if (this.state.isEmpty()) {
            invalidFields.add(
                InvalidFieldMetadata(addressType.name + SEPARATOR + UserProfileFieldName.STATE.name, "Value: $state should not be empty")
            )
        }

        if (this.zip.isEmpty()) {
            invalidFields.add(
                InvalidFieldMetadata(addressType.name + SEPARATOR + UserProfileFieldName.ZIP.name, "Value: $zip should not be empty")
            )
        }

        validateZip(this.zip, addressType).let {
            if (it.isNotEmpty()) {
                invalidFields.addAll(it)
            }
        }

        if (this.country.isEmpty()) {
            invalidFields.add(
                InvalidFieldMetadata(addressType.name + SEPARATOR + UserProfileFieldName.COUNTRY.name, "Value: $country should not be empty")
            )
        }

        return invalidFields

    }

    private val SEPARATOR = "."

    private fun isPanValid(pan: String): Boolean {
        if (pan.length != 10) {
            return false
        }

        return pan.matches(Regex("^[A-Z]{3}[TFHPC][A-Z][0-9]{4}[A-Z]$"))
    }

    // valid ein: XX-XXXXXXX where X is a digit from 0-9
    private fun isEinValid(ein: String): Boolean {
        val einParts = ein.split("-")

        return when {
            einParts.size != 2 -> false
            einParts[0].length != 2 -> false
            einParts[0].any { !it.isDigit() } -> false
            einParts[1].length != 7 -> false
            einParts[1].any { !it.isDigit() } -> false
            else -> true
        }
    }

    private fun validateZip(zip: String, addressType: AddressType): List<InvalidFieldMetadata> {
        val invalidFields = mutableListOf<InvalidFieldMetadata>()

        if (zip.length !in 5..6) {
            invalidFields.add(
                InvalidFieldMetadata(addressType.name + SEPARATOR + UserProfileFieldName.ZIP.name, "Value: $zip should be 5 or 6 character long")
            )
        }

        if (zip.any { !it.isDigit() }) {
            invalidFields.add(
                InvalidFieldMetadata(addressType.name + SEPARATOR + UserProfileFieldName.ZIP.name, "Value: $zip should be digits")
            )
        }

        return invalidFields
    }
