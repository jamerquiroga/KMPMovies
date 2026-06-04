package com.jquiroga.kmpmovies.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    @SerialName("title") val title: String,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("overview") val overview: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("vote_average") val voteAverage: Double
)