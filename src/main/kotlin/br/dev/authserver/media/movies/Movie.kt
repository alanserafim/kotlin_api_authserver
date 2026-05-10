package br.dev.authserver.media.movies

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class Movie (
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true)
    var tmdbId: Long? = null,

    @Column(nullable = false)
    var title: String = "",

    @Column(nullable = false, length = 2000)
    var overview: String = "",

    @Column(nullable = false)
    var releaseDate: LocalDate?,

    @Column(nullable = false)
    var posterPath: String = "",

    @Column(nullable = false)
    var backdropPath: String = "",

    @Column(nullable = false)
    var voteAverage: Double = 0.0,

    @Column(nullable = false)
    var voteCount: Int = 0,

    @Column(nullable = false)
    var popularity: Double = 0.0,

    @Column(nullable = false)
    var runtime: Int? = null
) {

}