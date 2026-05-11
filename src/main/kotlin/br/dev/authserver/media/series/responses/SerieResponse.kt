package br.dev.authserver.media.series.responses

import br.dev.authserver.media.series.Serie
import java.time.LocalDate

data class SerieResponse(
    val id: Long,
    val tmdbId: Long,
    val name: String,
    val overview: String
) {
    fun Serie.toResponse(): SerieResponse = SerieResponse(
        id = this.id!!,
        tmdbId = this.tmdbId!!,
        name = this.name,
        overview = this.overview,
    )

    constructor(Serie: Serie): this(
        id = Serie.id!!,
        tmdbId = Serie.tmdbId!!,
        name = Serie.name,
        overview = Serie.overview,
    )




}
