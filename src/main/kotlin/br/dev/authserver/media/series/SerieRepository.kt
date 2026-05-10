package br.dev.authserver.media.series

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SerieRepository: JpaRepository<Serie, Long> {

    fun findByTmdbId(tmdbId: Long): Serie?

    fun existsByTmdbId(tmdbId: Long?): Boolean

    fun findByNameContainingIgnoreCase(name: String): List<Serie>
}