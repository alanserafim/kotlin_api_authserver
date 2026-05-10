package br.dev.authserver.media.series

import org.springframework.web.bind.annotation.RestController

@RestController
class SerieController (
    val serieService: SerieService
) {
}