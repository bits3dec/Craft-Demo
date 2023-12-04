package demo.craft.user.profile.service.lock

import demo.craft.common.lock.LockManager
import demo.craft.user.profile.common.config.LoggingContext
import demo.craft.user.profile.common.config.UserProfileProperties
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class UserProfileLockManager(
    private val lockManager: LockManager,
    properties: UserProfileProperties
) {
    private val lockProperties = properties.lock

    @Transactional
    fun <T> doExclusively(userId: String, function: () -> T): T =
        LoggingContext.forUser(userId) {
            lockManager.tryWithLock("user", userId, lockProperties.timeout) {
                function()
            }
        }
}