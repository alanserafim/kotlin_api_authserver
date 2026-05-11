package br.dev.authserver.media.movies

import br.dev.authserver.media.movies.requests.MovieRequest
import br.dev.authserver.media.movies.responses.MovieResponse
import br.dev.authserver.users.responses.UserResponse
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
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movies")
class MovieController(
    val movieService: MovieService
) {
    @GetMapping
    fun list(): ResponseEntity<List<MovieResponse>> {
        val movies = movieService.findAll()
        return movies
            .map { MovieResponse(it) }
            .let { ResponseEntity.ok(it) }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Movie> =
        ResponseEntity.ok(movieService.findById(id))

    @GetMapping("/tmdb/{tmdbId}")
    fun getByTmdbId(
        @PathVariable tmdbId: Long
    ): ResponseEntity<Movie> =
        ResponseEntity.ok(movieService.findByTmdbId(tmdbId))

    @GetMapping("/search")
    fun searchByTitle(@RequestParam title: String): List<Movie> =
        movieService.findByTitle(title)

    @PostMapping
    fun insert(@RequestBody @Valid movie: MovieRequest) =
        movieService.insert(movie.toMovie())
            .let { MovieResponse(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @SecurityRequirement(name = "jwt-auth")
    @PreAuthorize("permitAll()")
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody @Valid movie: MovieRequest
    ): ResponseEntity<MovieResponse> {
        return movieService.update(id, movie.toMovie())
            ?.let { MovieResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.noContent().build()
    }


    @SecurityRequirement(name = "jwt-auth")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = movieService.delete(id)
}