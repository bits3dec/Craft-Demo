package demo.craft.user.profile.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import demo.craft.user.profile.client.model.RequestOperation;
import demo.craft.user.profile.client.model.RequestStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BusinessProfileRequestDetails
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-04T13:32:27.296+05:30[Asia/Kolkata]")

public class BusinessProfileRequestDetails   {
  @JsonProperty("requestId")
  private String requestId;

  @JsonProperty("operation")
  private RequestOperation operation;

  @JsonProperty("status")
  private RequestStatus status;

  public BusinessProfileRequestDetails requestId(String requestId) {
    this.requestId = requestId;
    return this;
  }

  /**
   * Get requestId
   * @return requestId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public BusinessProfileRequestDetails operation(RequestOperation operation) {
    this.operation = operation;
    return this;
  }

  /**
   * Get operation
   * @return operation
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public RequestOperation getOperation() {
    return operation;
  }

  public void setOperation(RequestOperation operation) {
    this.operation = operation;
  }

  public BusinessProfileRequestDetails status(RequestStatus status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public RequestStatus getStatus() {
    return status;
  }

  public void setStatus(RequestStatus status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BusinessProfileRequestDetails businessProfileRequestDetails = (BusinessProfileRequestDetails) o;
    return Objects.equals(this.requestId, businessProfileRequestDetails.requestId) &&
        Objects.equals(this.operation, businessProfileRequestDetails.operation) &&
        Objects.equals(this.status, businessProfileRequestDetails.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestId, operation, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessProfileRequestDetails {\n");
    
    sb.append("    requestId: ").append(toIndentedString(requestId)).append("\n");
    sb.append("    operation: ").append(toIndentedString(operation)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

