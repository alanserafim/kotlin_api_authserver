package br.dev.authserver.media.movies

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository: JpaRepository<Movie, Long> {

    fun findByTmdbId(tmdbId: Long?): Movie?

    fun existsByTmdbId(tmdbId: Long): Boolean

    fun findByTitleContainingIgnoreCase(title: String): List<Movie>
}