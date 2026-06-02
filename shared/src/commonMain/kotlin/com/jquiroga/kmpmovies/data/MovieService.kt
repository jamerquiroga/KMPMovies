package com.jquiroga.kmpmovies.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MovieService(
    private val client: HttpClient
) {

    suspend fun getPopularMovies(): MovieResultDto {
        return client
            .get("/movie/popular")
            .body<MovieResultDto>()
    }
}