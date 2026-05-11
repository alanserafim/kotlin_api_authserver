package br.dev.authserver.media.episodes

import br.dev.authserver.media.episodes.requests.EpisodeRequest
import br.dev.authserver.media.episodes.responses.EpisodeResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/episodes")
class EpisodeController(
    val episodeService: EpisodeService
) {
    @GetMapping
    fun list(): ResponseEntity<List<EpisodeResponse>>{
        val episodesList = episodeService.findAll()
        return episodesList
            .map { EpisodeResponse(it) }
            .let { ResponseEntity.ok(it) }
    }

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Long
    ) = episodeService.findById(id)
        .let { EpisodeResponse (it) }
        .let { ResponseEntity.ok(it) }

    @GetMapping("/serie/{serieId}")
    fun getBySerie(
        @PathVariable serieId: Long
    ) = episodeService.findBySerie(serieId)
        .map { EpisodeResponse(it) }
        .let { ResponseEntity.ok(it) }

    @PostMapping("/serie/{serieId}")
    fun insert(
        @PathVariable serieId: Long,
        @RequestBody @Valid episode: EpisodeRequest
    ) = episodeService.insert(serieId, episode)
        .let { EpisodeResponse(it) }
        .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @SecurityRequirement(name = "jwt-auth")
    @PreAuthorize("permitAll()")
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody episode: Episode
    ): ResponseEntity<Episode> =
        ResponseEntity.ok(episodeService.update(id, episode))

    @SecurityRequirement(name = "jwt-auth")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = episodeService.delete(id)
}