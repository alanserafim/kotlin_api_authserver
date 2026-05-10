package br.dev.authserver.media.episodes

import br.dev.authserver.media.series.Serie
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDate

@Entity
class Episode (
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true)
    val tmdbId: Long,

    @Column(nullable = false)
    val name: String = "",

    @Column(length = 1000)
    val overview: String?,

    @Column(nullable = false)
    val airDate: LocalDate?,

    @Column(nullable = false)
    val episodeNumber: Int,

    @Column(nullable = false)
    val seasonNumber: Int,

    @Column(nullable = false)
    val stillPath: String = "",

    @Column(nullable = false)
    val voteAverage: Double = 0.0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    @JsonIgnore
    val serie: Serie
) {
}