package demo.craft.user.profile.workflow.common.config

import java.time.Duration
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("demo.craft.user.profile.workflow")
class UserProfileWorkflowProperties {
    val kafka = KafkaProperties()
    val lock = LockProperties()
    val integration = IntegrationProperties()

    class KafkaProperties {
        // subscribe
        var userProfileRequestTopic: String = "user-profile-request"
        var userProfileValidationConfirmationTopic: String = "user-profile-validation-confirmation"

        // publish
        var userProfileValidationRequestTopic: String = "user-profile-validation-request"
        var userProfileRequestConfirmationTopic: String = "user-profile-request-confirmation"
    }

    class LockProperties {
        var timeoutDuration: Duration = Duration.ofSeconds(2)
    }

    class IntegrationProperties {
        var productRegistry = ProductRegistryProperties()

        class ProductRegistryProperties {
            var url: String = ""
        }
    }
}
