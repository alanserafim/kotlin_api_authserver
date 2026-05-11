package br.dev.authserver.media.series

import br.dev.authserver.media.episodes.Episode
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Serie (
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true)
    var tmdbId: Long? = null,

    @Column(nullable = false)
    var name: String = "",

    @Column(length = 2000)
    var overview: String = "",

    @OneToMany(mappedBy = "serie")
    var episodes: MutableList<Episode> = mutableListOf()

) {

}