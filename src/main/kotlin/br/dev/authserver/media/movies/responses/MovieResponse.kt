package br.dev.authserver.media.movies.responses

import br.dev.authserver.media.movies.Movie
import java.time.LocalDate

data class MovieResponse(
    val id: Long,
    val tmdbId: Long,
    val title: String,
    val overview: String,
) {
    fun Movie.toResponse(): MovieResponse = MovieResponse(
        id = this.id!!,
        tmdbId = this.tmdbId!!,
        title = this.title,
        overview = this.overview,
    )

    constructor(movie: Movie) : this(
        movie.id!!,
        movie.tmdbId!!,
        movie.title,
        movie.overview,
    )
}