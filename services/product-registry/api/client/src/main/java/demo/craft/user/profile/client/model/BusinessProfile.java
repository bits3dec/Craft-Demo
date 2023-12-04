package demo.craft.user.profile.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import demo.craft.user.profile.client.model.Address;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BusinessProfile
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-04T13:32:27.296+05:30[Asia/Kolkata]")

public class BusinessProfile   {
  @JsonProperty("companyName")
  private String companyName;

  @JsonProperty("legalName")
  private String legalName;

  @JsonProperty("businessAddress")
  private Address businessAddress;

  @JsonProperty("legalAddress")
  private Address legalAddress;

  @JsonProperty("pan")
  private String pan;

  @JsonProperty("ein")
  private String ein;

  @JsonProperty("email")
  private String email;

  @JsonProperty("website")
  private String website;

  public BusinessProfile companyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  /**
   * Get companyName
   * @return companyName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public BusinessProfile legalName(String legalName) {
    this.legalName = legalName;
    return this;
  }

  /**
   * Get legalName
   * @return legalName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getLegalName() {
    return legalName;
  }

  public void setLegalName(String legalName) {
    this.legalName = legalName;
  }

  public BusinessProfile businessAddress(Address businessAddress) {
    this.businessAddress = businessAddress;
    return this;
  }

  /**
   * Get businessAddress
   * @return businessAddress
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Address getBusinessAddress() {
    return businessAddress;
  }

  public void setBusinessAddress(Address businessAddress) {
    this.businessAddress = businessAddress;
  }

  public BusinessProfile legalAddress(Address legalAddress) {
    this.legalAddress = legalAddress;
    return this;
  }

  /**
   * Get legalAddress
   * @return legalAddress
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Address getLegalAddress() {
    return legalAddress;
  }

  public void setLegalAddress(Address legalAddress) {
    this.legalAddress = legalAddress;
  }

  public BusinessProfile pan(String pan) {
    this.pan = pan;
    return this;
  }

  /**
   * Get pan
   * @return pan
  */
  @ApiModelProperty(value = "")


  public String getPan() {
    return pan;
  }

  public void setPan(String pan) {
    this.pan = pan;
  }

  public BusinessProfile ein(String ein) {
    this.ein = ein;
    return this;
  }

  /**
   * Get ein
   * @return ein
  */
  @ApiModelProperty(value = "")


  public String getEin() {
    return ein;
  }

  public void setEin(String ein) {
    this.ein = ein;
  }

  public BusinessProfile email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public BusinessProfile website(String website) {
    this.website = website;
    return this;
  }

  /**
   * Get website
   * @return website
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BusinessProfile businessProfile = (BusinessProfile) o;
    return Objects.equals(this.companyName, businessProfile.companyName) &&
        Objects.equals(this.legalName, businessProfile.legalName) &&
        Objects.equals(this.businessAddress, businessProfile.businessAddress) &&
        Objects.equals(this.legalAddress, businessProfile.legalAddress) &&
        Objects.equals(this.pan, businessProfile.pan) &&
        Objects.equals(this.ein, businessProfile.ein) &&
        Objects.equals(this.email, businessProfile.email) &&
        Objects.equals(this.website, businessProfile.website);
  }

  @Override
  public int hashCode() {
    return Objects.hash(companyName, legalName, businessAddress, legalAddress, pan, ein, email, website);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessProfile {\n");
    
    sb.append("    companyName: ").append(toIndentedString(companyName)).append("\n");
    sb.append("    legalName: ").append(toIndentedString(legalName)).append("\n");
    sb.append("    businessAddress: ").append(toIndentedString(businessAddress)).append("\n");
    sb.append("    legalAddress: ").append(toIndentedString(legalAddress)).append("\n");
    sb.append("    pan: ").append(toIndentedString(pan)).append("\n");
    sb.append("    ein: ").append(toIndentedString(ein)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    website: ").append(toIndentedString(website)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

