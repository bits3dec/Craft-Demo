package demo.craft.user.profile.mapper

import demo.craft.common.domain.enums.TaxType
import demo.craft.common.domain.kafka.impl.AddressMessage
import demo.craft.common.domain.kafka.impl.TaxIdentifierMessage
import demo.craft.common.domain.kafka.impl.UserProfileMessage
import demo.craft.user.profile.model.BusinessProfile

fun BusinessProfile.toUserProfileMessage() =
    UserProfileMessage(
        companyName = this.companyName,
        legalName = this.legalName,
        businessAddress = this.businessAddress.toKafkaMessageModel(),
        legalAddress = this.legalAddress.toKafkaMessageModel(),
        taxIdentifier =
        if (this.pan != null) {
            TaxIdentifierMessage(value = this.pan!!, type = TaxType.PAN)
        } else {
            TaxIdentifierMessage(value = this.ein!!, type = TaxType.EIN)
        },
        email = this.email,
        website = this.website
    )

private fun demo.craft.user.profile.model.Address.toKafkaMessageModel(): AddressMessage =
    AddressMessage(
        line1 = this.line1,
        line2 = this.line2,
        city = this.city,
        state = this.state,
        zip = this.zip,
        country = this.country
    )