package demo.craft.user.profile.common.config

import java.time.Duration
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("demo.craft.user.profile")
class UserProfileProperties {
    val kafka = KafkaProperties()
    val lock = LockProperties()
    val cache = CacheProperties()

    class KafkaProperties {
        var userProfileRequestTopic: String = "user-profile-request"
        var userProfileRequestConfirmationTopic: String = "user-profile-request"
    }

    class LockProperties {
        var timeout: Duration = Duration.ofSeconds(2)
    }

    class CacheProperties {
        var defaultCacheTtlInSeconds: Duration = Duration.ofDays(7)
    }
}
