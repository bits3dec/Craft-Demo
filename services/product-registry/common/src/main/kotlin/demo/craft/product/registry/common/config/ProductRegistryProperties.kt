package demo.craft.product.registry.common.config

import java.time.Duration
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("demo.craft.product.registry")
class ProductRegistryProperties {
    val lock = LockProperties()

    class LockProperties {
        var timeoutDuration: Duration = Duration.ofSeconds(2)
    }
}
