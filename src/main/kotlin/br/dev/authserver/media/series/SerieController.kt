package br.dev.authserver.media.series

import br.dev.authserver.media.series.requests.SerieRequest
import br.dev.authserver.media.series.responses.SerieResponse
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/series")
class SerieController (
    val serieService: SerieService
) {
    @GetMapping
    fun list(): ResponseEntity<List<SerieResponse>> {
        val seriesList = serieService.findAll()
        return seriesList
            .map { SerieResponse(it) }
            .let { ResponseEntity.ok(it) }
    }


    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Long
    ) = serieService.findById(id)
        .let { SerieResponse(it) }
        .let { ResponseEntity.ok(it) }


    @GetMapping("/tmdb/{tmdbId}")
    fun getByTmdbId(
        @PathVariable tmdbId: Long
    ) = serieService.findByTmdbId(tmdbId)
        .let { SerieResponse(it) }
        .let { ResponseEntity.ok(it) }


    @GetMapping("/search")
    fun searchByTitle(
        @RequestParam name: String
    ) = serieService.findByTitle(name)
        .map { SerieResponse(it) }
        .let { ResponseEntity.ok(it) }

    @PostMapping
    fun insert(
        @RequestBody @Valid serie: SerieRequest
    ) = serieService.insert(serie.toSerie())
        .let { SerieResponse(it) }
        .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @SecurityRequirement(name = "jwt-auth")
    @PreAuthorize("permitAll()")
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody @Valid serie: SerieRequest
    ): ResponseEntity<SerieResponse> {
        return serieService.update(id, serie.toSerie())
            ?.let { SerieResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.noContent().build()
    }

    @SecurityRequirement(name = "jwt-auth")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = serieService.delete(id)

}