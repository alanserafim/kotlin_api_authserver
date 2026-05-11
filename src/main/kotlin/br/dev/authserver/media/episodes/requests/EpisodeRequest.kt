package br.dev.authserver.media.episodes.requests

import br.dev.authserver.media.episodes.Episode
import br.dev.authserver.media.series.Serie
import jakarta.validation.constraints.NotBlank

data class EpisodeRequest(
    val tmdbId: Long,
    @NotBlank
    val name: String,
    val overview: String?,
    val episodeNumber: Int,
    val seasonNumber: Int
) {
    fun toEpisode(serie: Serie): Episode = Episode(
        tmdbId = this.tmdbId,
        name = this.name,
        overview = this.overview,
        episodeNumber = this.episodeNumber,
        seasonNumber = this.seasonNumber,
        serie = serie
    )
}
