package br.dev.authserver.media.movies

import br.dev.authserver.exceptions.BadRequestException
import br.dev.authserver.exceptions.NotFoundException
import br.dev.authserver.users.UserService
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MovieService(
    private val movieRepository: MovieRepository
) {

    fun insert(movie: Movie): Movie {
        val movieId = movie.id
        if (movieId != null && movieRepository.existsById(movieId)) {
            throw BadRequestException("Filme com ID $movieId já cadastrado.")
        }
        return movieRepository.save(movie)
            .also {  log.info("Movie {} added.", it.id) }
    }

    fun findAll(): List<Movie> = movieRepository.findAll()

    fun findById(id: Long) = movieRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Filme não encontrado com ID: $id")


    fun findByTmdbId(tmdbId: Long) = movieRepository.findByTmdbId(tmdbId)
        ?: throw NotFoundException("Filme não encontrado com ID: $tmdbId")


    fun findByTitle(title: String) = movieRepository.findByTitleContainingIgnoreCase(title)

    @Transactional
    fun delete(id: Long) {
        val movie = findById(id)
        movieRepository.delete(movie)
        log.warn("Movie {} deleted.", id)
    }

    @Transactional
    fun update(id: Long, movieDetails: Movie): Movie {
        val existingMovie = findById(id)

        // Criamos um novo objeto baseado no existente (ou usamos o plugin JPA que abre as classes)
        val movieToUpdate = Movie(
            id = existingMovie.id,
            tmdbId = existingMovie.tmdbId,
            title = movieDetails.title,
            overview = movieDetails.overview,
            releaseDate = movieDetails.releaseDate,
            posterPath = movieDetails.posterPath,
            backdropPath = movieDetails.backdropPath,
            voteAverage = movieDetails.voteAverage,
            voteCount = movieDetails.voteCount,
            popularity = movieDetails.popularity,
            runtime = movieDetails.runtime
        )

        return movieRepository.save(movieToUpdate)
    }

    companion object {
        val log = LoggerFactory.getLogger(UserService::class.java)
    }

}

