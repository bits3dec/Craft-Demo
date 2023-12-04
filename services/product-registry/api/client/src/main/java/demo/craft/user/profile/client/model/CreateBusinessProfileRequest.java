package demo.craft.user.profile.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import demo.craft.user.profile.client.model.BusinessProfile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateBusinessProfileRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-04T13:32:27.296+05:30[Asia/Kolkata]")

public class CreateBusinessProfileRequest   {
  @JsonProperty("businessProfile")
  private BusinessProfile businessProfile;

  public CreateBusinessProfileRequest businessProfile(BusinessProfile businessProfile) {
    this.businessProfile = businessProfile;
    return this;
  }

  /**
   * Get businessProfile
   * @return businessProfile
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public BusinessProfile getBusinessProfile() {
    return businessProfile;
  }

  public void setBusinessProfile(BusinessProfile businessProfile) {
    this.businessProfile = businessProfile;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateBusinessProfileRequest createBusinessProfileRequest = (CreateBusinessProfileRequest) o;
    return Objects.equals(this.businessProfile, createBusinessProfileRequest.businessProfile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(businessProfile);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateBusinessProfileRequest {\n");
    
    sb.append("    businessProfile: ").append(toIndentedString(businessProfile)).append("\n");
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
