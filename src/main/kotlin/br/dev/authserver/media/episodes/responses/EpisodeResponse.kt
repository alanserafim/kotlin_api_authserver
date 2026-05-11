package br.dev.authserver.media.episodes.responses

import br.dev.authserver.media.episodes.Episode

data class EpisodeResponse(
    val id: Long,
    val tmdbId: Long,
    val name: String,
    val overview: String?,
    val episodeNumber: Int,
    val seasonNumber: Int,
    val serieId: Long
) {
    fun Episode.toResponse(): EpisodeResponse = EpisodeResponse(
        id = this.id!!,
        tmdbId = this.tmdbId,
        name = this.name,
        overview = this.overview,
        episodeNumber = this.episodeNumber,
        seasonNumber = this.seasonNumber,
        serieId = this.serie.id!!
    )
    constructor(episode: Episode) : this(
        episode.id!!,
        episode.tmdbId,
        episode.name,
        episode.overview,
        episode.episodeNumber,
        episode.seasonNumber,
        episode.serie.id!!
    )
}
