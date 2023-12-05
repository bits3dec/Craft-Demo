package demo.craft.user.profile.workflow.common.lock

import demo.craft.common.lock.LockManager
import demo.craft.user.profile.workflow.common.LoggingContext
import demo.craft.user.profile.workflow.common.config.UserProfileWorkflowProperties
import javax.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class UserProfileWorkflowLockManager(
    private val lockManager: LockManager,
    userProfileWorkflowProperties: UserProfileWorkflowProperties
) {
    private val lockProperties = userProfileWorkflowProperties.lock

    @Transactional
    fun <T> doExclusively(userId: String, function: () -> T): T =
        LoggingContext.forUser(userId) {
            lockManager.tryWithLock("user", userId, lockProperties.timeoutDuration) {
                function()
            }
        }
}