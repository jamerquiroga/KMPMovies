package com.jquiroga.kmpmovies.data

class MovieRepository(
    private val movieService: MovieService
) {

    suspend fun getPopularMovies(): MovieResultDto {
        return movieService.getPopularMovies()
    }

    suspend fun getMovieById(id: Int): MovieDetailDto {
        return movieService.getMovieById(id)
    }
}