package br.dev.authserver

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RootController {
    @GetMapping("/health")
    fun healthcheck() = mapOf("status" to "OK")
}