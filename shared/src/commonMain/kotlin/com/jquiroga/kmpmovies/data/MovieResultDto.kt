package com.jquiroga.kmpmovies.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResultDto(
    @SerialName("results") val results: List<MovieDto>
)