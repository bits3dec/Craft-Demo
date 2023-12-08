package demo.craft.user.profile.exception

import demo.craft.user.profile.common.LoggingContext
import demo.craft.user.profile.common.exception.*
import mu.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import kotlin.reflect.KClass

@ControllerAdvice
class RestResponseEntityExceptionHandler(
) : ResponseEntityExceptionHandler() {
    private val log = KotlinLogging.logger {}

    companion object {
        val DefaultErrorBody = ErrorBody("The request failed due to an internal error")
    }

    val exceptionToHttpStatusMapping: Map<KClass<out java.lang.RuntimeException>, HttpStatus> = mapOf(
        UserProfileNotFoundException::class to HttpStatus.NOT_FOUND,
        UserProfileRequestNotFoundException::class to HttpStatus.NOT_FOUND,
        UserProfileRequestAlreadyInProgressException::class to HttpStatus.CONFLICT,
        UserProfileAlreadyExistsException::class to HttpStatus.CONFLICT,
        InvalidUserProfileRequestException::class to HttpStatus.BAD_REQUEST,
        DuplicateUserProfileException::class to HttpStatus.CONFLICT
    )

    @ExceptionHandler
    protected fun handleException(e: RuntimeException, request: WebRequest): ResponseEntity<Any?>? {
        val apiErrorResponse = when (e) {
            is UserProfileException -> ApiErrorResponse(
                status = exceptionToHttpStatusMapping[e::class] ?: HttpStatus.INTERNAL_SERVER_ERROR,
                body = e.toErrorBody()
            )

            else -> ApiErrorResponse()
        }

        val userId = request.getHeader("x-user-id") ?: ""
        LoggingContext.forUser(userId) {
            if (apiErrorResponse.status == HttpStatus.INTERNAL_SERVER_ERROR) {
                // print stack trace in case of internal server error
                log.error(e) { "Internal server error: ${e.message}" }
            } else {
                log.error { e.message }
            }
        }

        return handleExceptionInternal(e, apiErrorResponse.body, HttpHeaders(), apiErrorResponse.status, request)
    }

    data class ApiErrorResponse(
        val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
        val body: ErrorBody = DefaultErrorBody
    )

    data class ErrorBody(
        val message: String,
    )

    private fun UserProfileException.toErrorBody() =
        ErrorBody(this.message)
}
