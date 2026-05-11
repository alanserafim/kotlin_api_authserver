package br.dev.authserver.media.series.requests

import br.dev.authserver.media.series.Serie
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class SerieRequest (
    val tmdbId: Long,

    @NotBlank
    val name: String,
    val overview: String,
) {
    fun toSerie(): Serie = Serie(
        tmdbId = this.tmdbId,
        name = this.name,
        overview = this.overview,
    )
}