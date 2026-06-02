package com.jquiroga.kmpmovies.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MovieService(
    private val apiKey: String,
    private val client: HttpClient
) {

    suspend fun getPopularMovies(): MovieResultDto {
        return client
            .get("https://api.themoviedb.org/3/movie/popular?api_key=$apiKey")
            .body<MovieResultDto>()
    }
}