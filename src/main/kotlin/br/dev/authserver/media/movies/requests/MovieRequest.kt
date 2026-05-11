package br.dev.authserver.media.movies.requests

import br.dev.authserver.media.movies.Movie
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class MovieRequest(
    val tmdbId: Long,
    @NotBlank
    val title: String,
    val overview: String,
) {
    fun toMovie(): Movie = Movie(
        tmdbId = this.tmdbId,
        title = this.title,
        overview = this.overview
    )
}
