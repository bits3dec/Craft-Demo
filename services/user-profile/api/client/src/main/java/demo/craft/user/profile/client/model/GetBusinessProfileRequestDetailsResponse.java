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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-04T13:32:27.296+05:30[Asia/Kolkata]")

public class GetBusinessProfileRequestDetailsResponse   {
  @JsonProperty("requestStatus")
  private BusinessProfileRequestDetails requestStatus;

  public GetBusinessProfileRequestDetailsResponse requestStatus(BusinessProfileRequestDetails requestStatus) {
    this.requestStatus = requestStatus;
    return this;
  }

  /**
   * Get requestStatus
   * @return requestStatus
  */
  @ApiModelProperty(value = "")

  @Valid

  public BusinessProfileRequestDetails getRequestStatus() {
    return requestStatus;
  }

  public void setRequestStatus(BusinessProfileRequestDetails requestStatus) {
    this.requestStatus = requestStatus;
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
    return Objects.equals(this.requestStatus, getBusinessProfileRequestDetailsResponse.requestStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetBusinessProfileRequestDetailsResponse {\n");
    
    sb.append("    requestStatus: ").append(toIndentedString(requestStatus)).append("\n");
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

