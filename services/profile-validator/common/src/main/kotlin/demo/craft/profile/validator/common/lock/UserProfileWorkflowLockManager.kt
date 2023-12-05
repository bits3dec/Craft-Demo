package demo.craft.profile.validator.common.lock

import demo.craft.common.lock.LockManager
import demo.craft.profile.validator.common.LoggingContext
import demo.craft.profile.validator.common.config.ProfileValidatorProperties
import javax.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class UserProfileWorkflowLockManager(
    private val lockManager: LockManager,
    profileValidatorProperties: ProfileValidatorProperties
) {
    private val lockProperties = profileValidatorProperties.lock

    @Transactional
    fun <T> doExclusively(userId: String, function: () -> T): T =
        LoggingContext.forUser(userId) {
            lockManager.tryWithLock("user", userId, lockProperties.timeoutDuration) {
                function()
            }
        }
}