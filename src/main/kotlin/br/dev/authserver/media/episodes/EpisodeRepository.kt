package br.dev.authserver.media.episodes

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EpisodeRepository: JpaRepository<Episode, Long> {

    fun findByTmdbId(tmdbId: Long): Episode?

    fun findBySerieId(seriesId: Long): List<Episode>

    fun findBySerieIdAndSeasonNumber(seriesId: Long, seasonNumber: Int): List<Episode>

    fun findByNameContainingIgnoreCase(title: String): List<Episode>
}