package demo.craft.user.profile.controller.validations

import demo.craft.common.domain.enums.TaxType
import demo.craft.common.domain.kafka.impl.AddressMessage
import demo.craft.common.domain.kafka.impl.TaxIdentifierMessage
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class UserProfileValidatorKtTest {

    private val validUserProfileMessage =
        UserProfileMessage(
            companyName = "COMPANY_1",
            legalName = "COMPANY_1",
            businessAddress =
                AddressMessage(
                    line1 = "Bank Colony",
                    line2 = "Near Super Mart",
                    city = "Bangalore",
                    state = "Karnataka",
                    country = "India",
                    zip = "12345",
                ),
            legalAddress =
                AddressMessage(
                    line1 = "Bank Colony",
                    line2 = "Near Super Mart",
                    city = "Bangalore",
                    state = "Karnataka",
                    country = "India",
                    zip = "12345",
                ),
            taxIdentifier =
                TaxIdentifierMessage(
                    type = TaxType.EIN,
                    value = "12-1234567"
                ),
            email = "asdqwed@asda.com",
            website = "qweqeqe.com"
        )

    @Test
    fun `test valid user profile`() {
        // given: valid user profile

        // when
        val invalidFields = validUserProfileMessage.validateFields()

        // then
        Assertions.assertEquals(0, invalidFields.count())
    }

    @Test
    fun `test invalid user profile with empty legal name`() {
        // given:
        val invalidUserProfile =
            validUserProfileMessage.copy(
                legalName = ""
            )

        // when
        val invalidFields = invalidUserProfile.validateFields()

        // then
        Assertions.assertEquals(1, invalidFields.count())
        Assertions.assertEquals(invalidFields[0].fieldName, "LEGAL_NAME")
    }

    @Test
    fun `test invalid user profile with invalid pan`() {
        // given
        val invalidUserProfile =
            validUserProfileMessage.copy(
                taxIdentifier =
                    TaxIdentifierMessage(
                        type =  TaxType.PAN,
                        value = "321312"
                    )
            )

        // when
        val invalidFields = invalidUserProfile.validateFields()

        // then
        Assertions.assertEquals(1, invalidFields.count())
        Assertions.assertEquals(invalidFields[0].fieldName, "PAN")
    }

    @Test
    fun `test invalid ein`() {
        // given
        val invalidUserProfile =
            validUserProfileMessage.copy(
                taxIdentifier =
                TaxIdentifierMessage(
                    type =  TaxType.EIN,
                    value = "321312"
                )
            )

        // when
        val invalidFields = invalidUserProfile.validateFields()

        // then
        Assertions.assertEquals(1, invalidFields.count())
        Assertions.assertEquals(invalidFields[0].fieldName, "EIN")
    }

    @Test
    fun `test invalid zip in address`() {
        // given
        val invalidUserProfile =
            validUserProfileMessage.copy(
                legalAddress =
                validUserProfileMessage.legalAddress.copy(
                    zip = "123"
                )
            )

        // when
        val invalidFields = invalidUserProfile.validateFields()

        // then
        Assertions.assertEquals(1, invalidFields.count())
        Assertions.assertEquals(invalidFields[0].fieldName, "LEGAL_ADDRESS.ZIP")
    }
}