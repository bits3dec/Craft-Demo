/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package demo.craft.user.profile.client.api;

import demo.craft.user.profile.client.model.GetBusinessProfileRequestDetailsResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-04T13:32:27.296+05:30[Asia/Kolkata]")

@Validated
@Api(value = "BusinessProfileRequest", description = "the BusinessProfileRequest API")
public interface BusinessProfileRequestApi {

    /**
     * GET /user-profile/v1/business-profile/request-details : Get business profile request details
     *
     * @param xUserId  (required)
     * @param requestId  (required)
     * @return Business profile request details (status code 200)
     */
    @ApiOperation(value = "Get business profile request details", nickname = "getBusinessProfileRequestDetails", notes = "", response = GetBusinessProfileRequestDetailsResponse.class, tags={ "business-profile-request", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Business profile request details", response = GetBusinessProfileRequestDetailsResponse.class) })
    @RequestMapping(value = "/user-profile/v1/business-profile/request-details",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<GetBusinessProfileRequestDetailsResponse> getBusinessProfileRequestDetails(@ApiParam(value = "" ,required=true) @RequestHeader(value="x-user-id", required=true) String xUserId,@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "requestId", required = true) String requestId);

}