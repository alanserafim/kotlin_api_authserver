package br.dev.authserver.media.episodes.responses

import br.dev.authserver.media.episodes.Episode

data class EpisodeResponse(
    val id: Long,
    val tmdbId: Long,
    val name: String,
    val overview: String?,
    val airDate: java.time.LocalDate?,
    val episodeNumber: Int,
    val seasonNumber: Int,
    val stillPath: String?,
    val voteAverage: Double,
    val serieId: Long
) {
    fun Episode.toResponse(): EpisodeResponse = EpisodeResponse(
        id = this.id!!,
        tmdbId = this.tmdbId,
        name = this.name,
        overview = this.overview,
        airDate = this.airDate,
        episodeNumber = this.episodeNumber,
        seasonNumber = this.seasonNumber,
        stillPath = this.stillPath,
        voteAverage = this.voteAverage,
        serieId = this.serie.id!!
    )
    constructor(episode: Episode) : this(
        episode.id!!,
        episode.tmdbId,
        episode.name,
        episode.overview,
        episode.airDate,
        episode.episodeNumber,
        episode.seasonNumber,
        episode.stillPath,
        episode.voteAverage,
        episode.id!!
    )
}
