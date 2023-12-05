package demo.craft.user.profile.workflow.common

import mu.withLoggingContext

object LoggingContext {
    inline fun <T> forUser(userId: String, function: () -> T): T =
        withLoggingContext(
            mapOf("userId" to userId),
            function
        )
}
