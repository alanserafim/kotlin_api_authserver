package br.dev.authserver.media.series

import br.dev.authserver.media.episodes.Episode
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.time.LocalDate

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

    @Column(nullable = false)
    var firstAirDate: LocalDate?,

    @Column(nullable = false)
    var posterPath: String = "",

    @Column(nullable = false)
    var backdropPath: String = "",

    @Column(nullable = false)
    var voteAverage: Double = 0.0,

    @Column(nullable = false)
    var numberOfEpisodes: Int = 0,

    @Column(nullable = false)
    var numberOfSeasons: Int = 0,

    @OneToMany(mappedBy = "serie")
    var episodes: MutableList<Episode> = mutableListOf()

) {

}