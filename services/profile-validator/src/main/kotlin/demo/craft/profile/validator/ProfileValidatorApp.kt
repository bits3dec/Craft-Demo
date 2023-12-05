package demo.craft.profile.validator

import demo.craft.common.communication.config.CommunicationProperties
import demo.craft.common.lock.config.LockManagerProperties
import demo.craft.profile.validator.common.config.ProfileValidatorProperties
import java.time.ZoneId
import java.util.TimeZone
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(
    scanBasePackages = [
        "demo.craft.profile.validator",
        "demo.craft.common.communication",
        "demo.craft.common.lock"
    ]
)
@ConfigurationPropertiesScan(
    basePackageClasses = [
        ProfileValidatorProperties::class,
        CommunicationProperties::class,
        LockManagerProperties::class,
    ]
)
@EnableJpaRepositories
class ProfileValidatorApp {
    init {
        TimeZone.setDefault(TIME_ZONE_UTC)
    }

    companion object {
        val TIME_ZONE_UTC: TimeZone = TimeZone.getTimeZone(ZoneId.of("UTC"))

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ProfileValidatorApp::class.java, *args)
        }
    }
}
