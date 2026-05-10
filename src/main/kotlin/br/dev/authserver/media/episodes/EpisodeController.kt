package br.dev.authserver.media.episodes

import org.springframework.web.bind.annotation.RestController

@RestController
class EpisodeController(
    val episodeService: EpisodeService
) {
}