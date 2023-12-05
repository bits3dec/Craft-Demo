package demo.craft.profile.validator.common.config

import java.time.Duration
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("demo.craft.profile.validator")
class ProfileValidatorProperties {
    val kafka = KafkaProperties()
    val lock = LockProperties()

    class KafkaProperties {
        // subscribe
        var userProfileValidationRequestTopic: String = "user-profile-validation-request"

        // publish
        var userProfileValidationConfirmationTopic: String = "user-profile-validation-confirmation"
    }

    class LockProperties {
        var timeoutDuration: Duration = Duration.ofSeconds(2)
    }
}
