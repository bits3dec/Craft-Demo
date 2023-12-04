package demo.craft.user.profile.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateBusinessProfileResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-04T13:32:27.296+05:30[Asia/Kolkata]")

public class CreateBusinessProfileResponse   {
  @JsonProperty("requestUuid")
  private String requestUuid;

  public CreateBusinessProfileResponse requestUuid(String requestUuid) {
    this.requestUuid = requestUuid;
    return this;
  }

  /**
   * Get requestUuid
   * @return requestUuid
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getRequestUuid() {
    return requestUuid;
  }

  public void setRequestUuid(String requestUuid) {
    this.requestUuid = requestUuid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateBusinessProfileResponse createBusinessProfileResponse = (CreateBusinessProfileResponse) o;
    return Objects.equals(this.requestUuid, createBusinessProfileResponse.requestUuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestUuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateBusinessProfileResponse {\n");
    
    sb.append("    requestUuid: ").append(toIndentedString(requestUuid)).append("\n");
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

