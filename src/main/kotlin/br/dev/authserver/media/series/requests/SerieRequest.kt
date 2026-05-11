package br.dev.authserver.media.series.requests

import br.dev.authserver.media.series.Serie
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class SerieRequest (
    val tmdbId: Long,

    @NotBlank
    val name: String,
    val overview: String,
    val firstAirDate: LocalDate?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double = 0.0,
    val numberOfEpisodes: Int = 0,
    val numberOfSeasons: Int = 0
) {
    fun toSerie(): Serie = Serie(
        tmdbId = this.tmdbId,
        name = this.name,
        overview = this.overview,
        firstAirDate = this.firstAirDate,
        posterPath = this.posterPath ?: "",
        backdropPath = this.backdropPath ?: "",
        voteAverage = this.voteAverage,
        numberOfEpisodes = this.numberOfEpisodes,
        numberOfSeasons = this.numberOfSeasons
    )
}