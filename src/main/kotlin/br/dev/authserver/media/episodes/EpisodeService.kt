package br.dev.authserver.media.episodes

import br.dev.authserver.exceptions.BadRequestException
import br.dev.authserver.exceptions.NotFoundException
import br.dev.authserver.media.episodes.requests.EpisodeRequest
import br.dev.authserver.media.movies.MovieService
import br.dev.authserver.media.series.SerieRepository
import br.dev.authserver.users.UserService
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EpisodeService(
    private val episodeRepository: EpisodeRepository,
    private val serieRepository: SerieRepository
) {
    @Transactional
    fun insert(serieId: Long, episode: EpisodeRequest): Episode {
        val serie = serieRepository.findByIdOrNull(serieId)
            ?: throw NotFoundException("Serie not found.")
        if (episodeRepository.findByTmdbId(episode.tmdbId) != null) {
            throw BadRequestException("Episode tmdbId ${episode.tmdbId} already exists.")
        }
        val episodeToSave = Episode(
            tmdbId = episode.tmdbId,
            name = episode.name,
            overview = episode.overview,
            episodeNumber = episode.episodeNumber,
            seasonNumber = episode.seasonNumber,
            serie = serie
        )
        return episodeRepository.save(episodeToSave)
            .also {  log.info("Episode {} added.", it.id) }

    }

    fun findAll(): List<Episode> = episodeRepository.findAll()

    fun findById(id: Long) = episodeRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Episode not found ID: $id")


    fun findByTmdbId(tmdbId: Long) = episodeRepository.findByTmdbId(tmdbId)
        ?: throw NotFoundException("Episode not found tmdbId: $tmdbId")

    fun findByTitle(title: String) = episodeRepository.findByNameContainingIgnoreCase(title)

    fun findBySerie(serieId: Long) = episodeRepository.findBySerieId(serieId)


    @Transactional
    fun delete(id: Long) {
        val episode = findById(id)
        episodeRepository.delete(episode)
        log.warn("episode {} deleted.", id)
    }

    @Transactional
    fun update(id: Long, episodeDetails: EpisodeRequest): Episode {
        val existingEpisode = findById(id)

        val updatedEpisode = Episode(
            id = existingEpisode.id,
            tmdbId = existingEpisode.tmdbId,
            name = episodeDetails.name,
            overview = episodeDetails.overview,
            episodeNumber = episodeDetails.episodeNumber,
            seasonNumber = episodeDetails.seasonNumber,
            serie = existingEpisode.serie
        )

        return episodeRepository.save(updatedEpisode)

    }

    companion object {
        val log = LoggerFactory.getLogger(UserService::class.java)
    }
}