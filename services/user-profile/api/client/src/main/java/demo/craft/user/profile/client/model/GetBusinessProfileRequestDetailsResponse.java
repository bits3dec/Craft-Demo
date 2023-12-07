package demo.craft.user.profile.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import demo.craft.user.profile.client.model.BusinessProfileRequestDetails;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetBusinessProfileRequestDetailsResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-07T14:53:57.605242+05:30[Asia/Kolkata]")

public class GetBusinessProfileRequestDetailsResponse   {
  @JsonProperty("requestDetails")
  private BusinessProfileRequestDetails requestDetails;

  public GetBusinessProfileRequestDetailsResponse requestDetails(BusinessProfileRequestDetails requestDetails) {
    this.requestDetails = requestDetails;
    return this;
  }

  /**
   * Get requestDetails
   * @return requestDetails
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public BusinessProfileRequestDetails getRequestDetails() {
    return requestDetails;
  }

  public void setRequestDetails(BusinessProfileRequestDetails requestDetails) {
    this.requestDetails = requestDetails;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetBusinessProfileRequestDetailsResponse getBusinessProfileRequestDetailsResponse = (GetBusinessProfileRequestDetailsResponse) o;
    return Objects.equals(this.requestDetails, getBusinessProfileRequestDetailsResponse.requestDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetBusinessProfileRequestDetailsResponse {\n");
    
    sb.append("    requestDetails: ").append(toIndentedString(requestDetails)).append("\n");
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

