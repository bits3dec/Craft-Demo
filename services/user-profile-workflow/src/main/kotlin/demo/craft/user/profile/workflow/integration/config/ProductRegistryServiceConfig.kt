package demo.craft.user.profile.workflow.integration.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import demo.craft.product.registry.client.api.ProductRegistryApi
import demo.craft.user.profile.workflow.common.config.UserProfileWorkflowProperties
import feign.Contract
import feign.Feign
import feign.Logger
import feign.Retryer
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder
import org.springframework.cloud.openfeign.support.SpringMvcContract
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProductRegistryServiceConfig(
    userProfileWorkflowProperties: UserProfileWorkflowProperties
) {
    private val objectMapper = jacksonObjectMapper().apply {
        registerModule(JavaTimeModule()) // Register JavaTimeModule to handle Java 8 date/time types
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        configure(DeserializationFeature. FAIL_ON_UNKNOWN_PROPERTIES, false)
    }
    private val integrationProperties = userProfileWorkflowProperties.integration

    @Bean
    fun ProductRegistryApi(): ProductRegistryApi =
        getFeignBuilder(SpringMvcContract()).target(
            ProductRegistryApi::class.java,
            integrationProperties.productRegistry.url
        )

    private fun getFeignBuilder(contract: Contract): Feign.Builder =
        Feign.builder()
            .logLevel(Logger.Level.FULL)
            .decoder(ResponseEntityDecoder(JacksonDecoder(objectMapper)))
            .encoder(JacksonEncoder(objectMapper))
            .retryer(Retryer.NEVER_RETRY)
            .requestInterceptor {
                it.header("Content-Type", "application/json")
                it.header("Accept", "application/json")
            }
            .contract(contract)
}