package com.jquiroga.kmpmovies.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieService(
    private val client: HttpClient
) {

    suspend fun getPopularMovies(
        region: String
    ): MovieResultDto {
        return client
            .get("/movie/popular") {
                parameter("region", region)
            }
            .body<MovieResultDto>()
    }

    suspend fun getMovieById(id: Int): MovieDetailDto {
        return client
            .get("/movie/$id")
            .body<MovieDetailDto>()
    }
}