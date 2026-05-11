package br.dev.authserver.media.movies.requests

import br.dev.authserver.media.movies.Movie
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class MovieRequest(
    val tmdbId: Long,
    @NotBlank
    val title: String,
    val overview: String,
    val releaseDate: LocalDate?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val popularity: Double = 0.0,
    val runtime: Int?
) {
    fun toMovie(): Movie = Movie(
        tmdbId = this.tmdbId,
        title = this.title,
        overview = this.overview,
        releaseDate = this.releaseDate,
        posterPath = this.posterPath ?: "",
        backdropPath = this.backdropPath ?: "",
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        popularity = this.popularity,
        runtime = this.runtime
    )
}
