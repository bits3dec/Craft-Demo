package demo.craft.product.registry

import demo.craft.common.lock.config.LockManagerProperties
import demo.craft.product.registry.common.config.ProductRegistryProperties
import java.time.ZoneId
import java.util.TimeZone
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(
    scanBasePackages = [
        "demo.craft.product.registry",
        "demo.craft.common.lock"
    ]
)
@ConfigurationPropertiesScan(
    basePackageClasses = [
        ProductRegistryProperties::class,
        LockManagerProperties::class,
    ]
)
@EnableJpaRepositories
class ProductRegistryApp {
    init {
        TimeZone.setDefault(TIME_ZONE_UTC)
    }

    companion object {
        val TIME_ZONE_UTC: TimeZone = TimeZone.getTimeZone(ZoneId.of("UTC"))

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ProductRegistryApp::class.java, *args)
        }
    }
}
