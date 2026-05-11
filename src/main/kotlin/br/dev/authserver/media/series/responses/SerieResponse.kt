package br.dev.authserver.media.series.responses

import br.dev.authserver.media.series.Serie
import java.time.LocalDate

data class SerieResponse(
    val id: Long,
    val tmdbId: Long,
    val name: String,
    val overview: String,
    val firstAirDate: LocalDate?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int
) {
    fun Serie.toResponse(): SerieResponse = SerieResponse(
        id = this.id!!,
        tmdbId = this.tmdbId!!,
        name = this.name,
        overview = this.overview,
        firstAirDate = this.firstAirDate,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath,
        voteAverage = this.voteAverage,
        numberOfEpisodes = this.numberOfEpisodes,
        numberOfSeasons = this.numberOfSeasons
    )

    constructor(Serie: Serie): this(
        id = Serie.id!!,
        tmdbId = Serie.tmdbId!!,
        name = Serie.name,
        overview = Serie.overview,
        firstAirDate = Serie.firstAirDate,
        posterPath = Serie.posterPath,
        backdropPath = Serie.backdropPath,
        voteAverage = Serie.voteAverage,
        numberOfEpisodes = Serie.numberOfEpisodes,
        numberOfSeasons = Serie.numberOfSeasons
    )




}
