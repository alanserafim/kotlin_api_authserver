package br.dev.authserver.media.movies

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

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
) {

}