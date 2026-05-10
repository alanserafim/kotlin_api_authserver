package br.dev.authserver.media.movies.responses

import br.dev.authserver.media.movies.Movie
import java.time.LocalDate

data class MovieResponse(
    val id: Long,
    val tmdbId: Long,
    val title: String,
    val overview: String,
    val releaseDate: LocalDate?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val voteCount: Int,
    val popularity: Double,
    val runtime: Int?
) {
    fun Movie.toResponse(): MovieResponse = MovieResponse(
        id = this.id!!,
        tmdbId = this.tmdbId!!,
        title = this.title,
        overview = this.overview,
        releaseDate = this.releaseDate,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        popularity = this.popularity,
        runtime = this.runtime
    )

    constructor(movie: Movie) : this(
        movie.id!!,
        movie.tmdbId!!,
        movie.title,
        movie.overview,
        movie.releaseDate,
        movie.posterPath,
        movie.backdropPath,
        movie.voteAverage,
        movie.voteCount,
        movie.popularity,
        movie.runtime
    )
}