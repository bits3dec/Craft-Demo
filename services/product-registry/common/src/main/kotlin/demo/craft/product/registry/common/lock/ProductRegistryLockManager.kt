package demo.craft.product.registry.common.lock

import demo.craft.common.lock.LockManager
import demo.craft.product.registry.common.LoggingContext
import demo.craft.product.registry.common.config.ProductRegistryProperties
import javax.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class ProductRegistryLockManager(
    private val lockManager: LockManager,
    properties: ProductRegistryProperties
) {
    private val lockProperties = properties.lock

    @Transactional
    fun <T> doExclusively(userId: String, function: () -> T): T =
        LoggingContext.forUser(userId) {
            lockManager.tryWithLock("user", userId, lockProperties.timeoutDuration) {
                function()
            }
        }
}