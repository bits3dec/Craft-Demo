package demo.craft.user.profile.common.config

import java.time.Duration
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("demo.craft.user.profile")
class UserProfileProperties {
    val kafka = KafkaProperties()
    val lock = LockProperties()
    val cache = CacheProperties()

    class KafkaProperties {
        // publish
        var userProfileRequestTopic: String = "user-profile-request"

        // subscribe
        var userProfileRequestConfirmationTopic: String = "user-profile-request-confirmation"
    }

    class LockProperties {
        var timeoutDuration: Duration = Duration.ofSeconds(2)
    }

    class CacheProperties {
        var defaultCacheTtlDuration: Duration = Duration.ofDays(7)
    }
}
