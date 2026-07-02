package br.dev.authserver.integration.quotes

import com.fasterxml.jackson.annotation.JsonProperty

data class Quote(
    @JsonProperty("q")
    val text: String
)
