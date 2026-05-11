package br.dev.authserver.media.series

import br.dev.authserver.exceptions.BadRequestException
import br.dev.authserver.exceptions.NotFoundException
import br.dev.authserver.media.movies.MovieService
import br.dev.authserver.users.UserService
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SerieService(
    private val serieRepository: SerieRepository
) {
    @Transactional
    fun insert(serie: Serie): Serie {
        val serieId = serie.id
        if (serieId != null && serieRepository.existsById(serieId)) {
            throw BadRequestException("Série com TMDB ID ${serie.id} já cadastrada.")
        }
        return serieRepository.save(serie)
            .also {  log.info("Serie {} added.", it.id) }
    }

    fun findAll(): List<Serie> = serieRepository.findAll()

    fun findById(id: Long) = serieRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Série não encontrada com ID: $id")

    fun findByTmdbId(tmdbId: Long) = serieRepository.findByTmdbId(tmdbId)
        ?: throw NotFoundException("Série não encontrada com TMDB ID: $tmdbId")


    fun findByTitle(name: String) = serieRepository.findByNameContainingIgnoreCase(name)

    @Transactional
    fun delete(id: Long) {
        val serie = findById(id)
        serieRepository.delete(serie)
        log.warn("Serie {} deleted.", id)
    }

    @Transactional
    fun update(id: Long, serieDetails: Serie): Serie {
        val existingSeries = findById(id)

        val updatedSeries = Serie(
            id = existingSeries.id,
            tmdbId = serieDetails.tmdbId,
            name = serieDetails.name,
            overview = serieDetails.overview,
            firstAirDate = serieDetails.firstAirDate,
            posterPath = serieDetails.posterPath,
            backdropPath = serieDetails.backdropPath,
            voteAverage = serieDetails.voteAverage,
            numberOfEpisodes = serieDetails.numberOfEpisodes,
            numberOfSeasons = serieDetails.numberOfSeasons
        )

        return serieRepository.save(updatedSeries)
    }

    companion object {
        val log = LoggerFactory.getLogger(UserService::class.java)
    }


}