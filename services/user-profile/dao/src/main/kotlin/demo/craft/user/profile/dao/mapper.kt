package demo.craft.user.profile.dao

import demo.craft.common.domain.enums.TaxType
import demo.craft.common.domain.kafka.impl.AddressMessage
import demo.craft.common.domain.kafka.impl.TaxIdentifierMessage
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.user.profile.domain.entity.Address
import demo.craft.user.profile.domain.entity.TaxIdentifier
import demo.craft.user.profile.domain.entity.UserProfile

fun UserProfileMessage.toUserProfile(userId: String) =
    UserProfile(
        userId = userId,
        companyName = this.companyName,
        legalName = this.legalName,
        businessAddress = this.businessAddress.toAddress(),
        legalAddress = this.legalAddress.toAddress(),
        taxIdentifier = this.taxIdentifier.toTaxIdentifier(),
        email = this.email,
        website = this.website
    )

fun AddressMessage.toAddress() =
    Address(
        line1 = this.line1,
        line2 = this.line2,
        city = this.city,
        state = this.state,
        country = this.country,
        zip = this.zip
    )

fun TaxIdentifierMessage.toTaxIdentifier() =
    when (this.type) {
        TaxType.PAN -> TaxIdentifier(type = TaxType.PAN, value = this.value)
        TaxType.EIN -> TaxIdentifier(type = TaxType.EIN, value = this.value)
    }