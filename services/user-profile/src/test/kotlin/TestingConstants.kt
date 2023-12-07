package demo.craft.user.constants

import demo.craft.common.domain.enums.TaxType
import demo.craft.common.domain.kafka.impl.TaxIdentifierMessage
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.user.profile.domain.entity.Address
import demo.craft.user.profile.domain.entity.TaxIdentifier
import demo.craft.user.profile.domain.entity.UserProfile

val USER_1 = "USER_1"
val COMPANY_NAME_1 = "COMPANY_NAME_1"
val LEGAL_NAME_1 = "LEGAL_NAME_1"
val LEGAL_ADDRESS_1 =
    Address(
        line1 = "Bank Colony",
        line2 = "Near Super Mart",
        city = "Bangalore",
        state = "Karnataka",
        country = "India",
        zip = "12345",
    )
val BUSINESS_ADDRESS_1 =
    Address(
        line1 = "Bank Colony",
        line2 = "Near Commercial Stree",
        city = "Bangalore",
        state = "Karnataka",
        country = "India",
        zip = "43242",
    )
val PAN_1 =
    TaxIdentifier(
        type = TaxType.PAN,
        value = "ABFQW1234A"
    )
val EIN_1 =
    TaxIdentifier(
        type = TaxType.PAN,
        value = "1-1234567"
    )
val EMAIL_1 = "asdasd@asdasd.com"
val WEBSITE_1 = "www.awqeqeqwe.com"

val USER_PROFILE =
    UserProfile(
        userId = USER_1,
        companyName = COMPANY_NAME_1,
        legalName = LEGAL_NAME_1,
        businessAddress = BUSINESS_ADDRESS_1,
        legalAddress = LEGAL_ADDRESS_1,
        taxIdentifier = PAN_1,
        email = EMAIL_1,
        website = WEBSITE_1
    )

val USER_PROFILE_MESSAGE =
    UserProfileMessage(
        userId = USER_1,
        companyName = COMPANY_NAME_1,
        legalName = LEGAL_NAME_1,
        businessAddress = BUSINESS_ADDRESS_1,
        legalAddress = LEGAL_ADDRESS_1,
        taxIdentifier = PAN_1,
        email = EMAIL_1,
        website = WEBSITE_1
    )
