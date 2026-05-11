package br.dev.authserver.media.episodes.requests

import br.dev.authserver.media.episodes.Episode
import br.dev.authserver.media.series.Serie
import jakarta.validation.constraints.NotBlank

data class EpisodeRequest(
    val tmdbId: Long,
    @NotBlank
    val name: String,
    val overview: String?,
    val airDate: java.time.LocalDate?,
    val episodeNumber: Int,
    val seasonNumber: Int,
    val stillPath: String?,
    val voteAverage: Double = 0.0
) {
    fun toEpisode(serie: Serie): Episode = Episode(
        tmdbId = this.tmdbId,
        name = this.name,
        overview = this.overview,
        airDate = this.airDate,
        episodeNumber = this.episodeNumber,
        seasonNumber = this.seasonNumber,
        stillPath = this.stillPath ?: "",
        voteAverage = this.voteAverage,
        serie = serie
    )
}
