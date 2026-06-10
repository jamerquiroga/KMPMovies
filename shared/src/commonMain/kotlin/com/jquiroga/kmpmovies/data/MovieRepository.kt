package com.jquiroga.kmpmovies.data

import com.jquiroga.kmpmovies.data.database.MovieEntity
import com.jquiroga.kmpmovies.data.database.MoviesDao
import kotlinx.coroutines.flow.onEach

class MovieRepository(
    private val movieService: MovieService,
    private val moviesDao: MoviesDao,
    private val regionRepository: RegionRepository
) {

    val movies = moviesDao.fetchPopularMovies().onEach { movies ->
        if (movies.isEmpty()) {
            val popularMovies = movieService.getPopularMovies(
                region = regionRepository.fetchRegion()
            ).results.map {
                MovieEntity(
                    id = it.id,
                    title = it.title,
                    posterPath = it.posterPath.orEmpty(),
                )
            }
            moviesDao.save(popularMovies)
        }
    }

    suspend fun getMovieById(id: Int): MovieDetailDto {
        return movieService.getMovieById(id)
    }
}