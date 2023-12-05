package demo.craft.user.profile.mapper

import demo.craft.common.domain.enums.Operation
import demo.craft.common.domain.enums.State
import demo.craft.common.domain.enums.TaxType
import demo.craft.user.profile.domain.entity.Address
import demo.craft.user.profile.domain.entity.TaxIdentifier
import demo.craft.user.profile.domain.entity.UserProfile
import demo.craft.user.profile.domain.entity.UserProfileRequest
import demo.craft.user.profile.model.BusinessProfile
import demo.craft.user.profile.model.BusinessProfileRequestDetails

fun UserProfile.toApiModel(): demo.craft.user.profile.model.BusinessProfile =
    BusinessProfile(
        companyName = this.companyName,
        legalName = this.legalName,
        businessAddress = this.businessAddress.toApiModel(),
        legalAddress = this.legalAddress.toApiModel(),
        pan = if (this.taxIdentifier.type == TaxType.PAN) this.taxIdentifier.value else null,
        ein = if (this.taxIdentifier.type == TaxType.EIN) this.taxIdentifier.value else null,
        email = this.email,
        website = this.website,
    )

fun demo.craft.user.profile.model.BusinessProfile.toDomainModel(userId: String): UserProfile =
    UserProfile(
        userId = userId,
        companyName = this.companyName,
        legalName = this.legalName,
        businessAddress = this.businessAddress.toDomainModel(),
        legalAddress = this.legalAddress.toDomainModel(),
        taxIdentifier = if (this.pan != null) {
            TaxIdentifier(value = this.pan!!, type = TaxType.PAN)
        } else {
            TaxIdentifier(value = this.ein!!, type = TaxType.EIN)
        },
        email = this.email,
        website = this.website,
    )

fun UserProfileRequest.toApiModel(): demo.craft.user.profile.model.BusinessProfileRequestDetails =
    BusinessProfileRequestDetails(
        requestId = this.requestId,
        operation = this.operation.toApiModel(),
        status = this.state.toApiModel(),
    )

private fun Operation.toApiModel() =
    demo.craft.user.profile.model.RequestOperation.valueOf(this.name)

private fun State.toApiModel() =
    demo.craft.user.profile.model.RequestStatus.valueOf(this.name)


private fun Address.toApiModel(): demo.craft.user.profile.model.Address =
    demo.craft.user.profile.model.Address(
        line1 = this.line1,
        line2 = this.line2,
        city = this.city,
        state = this.state,
        zip = this.zip,
        country = this.country
    )

private fun demo.craft.user.profile.model.Address.toDomainModel(): Address =
    Address(
        line1 = this.line1,
        line2 = this.line2,
        city = this.city,
        state = this.state,
        zip = this.zip,
        country = this.country
    )


