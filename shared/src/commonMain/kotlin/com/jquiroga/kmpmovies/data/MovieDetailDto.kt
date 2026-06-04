package com.jquiroga.kmpmovies.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    @SerialName("title") val title: String,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("overview") val overview: String
)