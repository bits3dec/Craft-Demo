package demo.craft.user.profile.common.cache

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.common.cache.CacheManager
import demo.craft.user.profile.common.config.UserProfileProperties
import java.time.Duration
import org.springframework.stereotype.Component

@Component
class GenericCacheManager(
    private val cacheManager: CacheManager,
    userProfileProperties: UserProfileProperties
) {
    private val cacheProperties = userProfileProperties.cache

    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        configure(DeserializationFeature. FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    /**
     * 1. Checks if key is present in the cache or not.
     * 2. If key is present then return the value.
     * 3. If key is not present then it is a cache miss.
     *    Now fetch from the main source and then update the cache.
     */
    fun <T : Any> lookup(
        key: String,
        valueTypeRef: TypeReference<T>,
        ttl: Duration = cacheProperties.defaultCacheTtlDuration,
        onCacheMiss: () -> T?
    ): T? =
        cacheManager.get(key)?.let { valueString ->
            objectMapper.readValue(valueString, valueTypeRef)
        } ?: onCacheMiss()?.also { value ->
            update(key, valueTypeRef, value)
        }

    private fun <T> update(
        key: String,
        valueTypeRef: TypeReference<T>,
        cacheValue: T,
        ttl: Duration = cacheProperties.defaultCacheTtlDuration
    ) =
        cacheManager.put(key, objectMapper.writeValueAsString(cacheValue), ttl.seconds)
}