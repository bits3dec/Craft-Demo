package demo.craft.user.profile.service

import com.fasterxml.jackson.core.type.TypeReference
import demo.craft.common.cache.CacheManager
import demo.craft.user.constants.*
import demo.craft.user.profile.common.cache.GenericCacheManager
import demo.craft.user.profile.common.config.UserProfileProperties
import demo.craft.user.profile.dao.UserProfileAccess
import demo.craft.user.profile.domain.entity.UserProfile
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.Duration

@ExtendWith(SpringExtension::class)
internal class UserProfileServiceTest {

    @MockK
    private lateinit var userProfileAccess: UserProfileAccess

    @InjectMockKs
    private lateinit var cacheManager: GenericCacheManager

    @MockK
    private lateinit var cacheManager2: CacheManager

    @SpyK
    private var userProfileProperties: UserProfileProperties = UserProfileProperties()

    @InjectMockKs
    private lateinit var userProfileService: UserProfileService

    // TODO: Fix the test
    // @Test
    fun getUserProfile() {
        // given
        every {
            cacheManager.lookup(
                any(),
                object : TypeReference<UserProfile>(){},
                Duration.ZERO,
                any()
            )
        } returns USER_PROFILE

        // when
        val actual = userProfileService.getUserProfile(USER_1)

        // then
        assertEquals(USER_PROFILE, actual)
    }

}