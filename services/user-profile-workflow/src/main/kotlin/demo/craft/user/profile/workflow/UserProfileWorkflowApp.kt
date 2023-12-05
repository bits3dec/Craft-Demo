package demo.craft.user.profile.workflow

import demo.craft.common.communication.config.CommunicationProperties
import demo.craft.common.lock.config.LockManagerProperties
import demo.craft.user.profile.workflow.common.config.UserProfileWorkflowProperties
import java.time.ZoneId
import java.util.TimeZone
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(
    scanBasePackages = [
        "demo.craft.user.profile.workflow",
        "demo.craft.common.communication",
        "demo.craft.common.lock"
    ]
)
@ConfigurationPropertiesScan(
    basePackageClasses = [
        UserProfileWorkflowProperties::class,
        CommunicationProperties::class,
        LockManagerProperties::class
    ]
)
@EnableJpaRepositories
class UserProfileWorkflowApp {
    init {
        TimeZone.setDefault(TIME_ZONE_UTC)
    }

    companion object {
        val TIME_ZONE_UTC: TimeZone = TimeZone.getTimeZone(ZoneId.of("UTC"))

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(UserProfileWorkflowApp::class.java, *args)
        }
    }
}
