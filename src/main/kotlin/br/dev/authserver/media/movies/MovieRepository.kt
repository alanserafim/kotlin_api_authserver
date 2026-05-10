package br.dev.authserver.media.movies

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository: JpaRepository<Movie, Long> {

    // Busca um filme pelo ID oficial do TMDB
    fun findByTmdbId(tmdbId: Long?): Movie?

    // Verifica se o filme já existe para evitar inserts duplicados
    fun existsByTmdbId(tmdbId: Long): Boolean

    // Busca parcial por título (útil para sua busca interna)
    fun findByTitleContainingIgnoreCase(title: String): List<Movie>
}