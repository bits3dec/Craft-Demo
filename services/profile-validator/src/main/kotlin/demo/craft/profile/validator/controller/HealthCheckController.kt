package demo.craft.profile.validator.controller

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {
    private val log = KotlinLogging.logger {}

    @GetMapping("/health")
    fun health(): String {
        log.debug { "Ping received for health check" }
        return "Ok"
    }
}