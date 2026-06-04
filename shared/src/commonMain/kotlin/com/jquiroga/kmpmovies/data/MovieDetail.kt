package com.jquiroga.kmpmovies.data

data class MovieDetail(
    val title: String = "",
    val overview: String = "",
    val backdropPath: String = "",
    val releaseDate: String = "",
    val originalTitle: String = "",
    val originalLanguage: String = "",
    val popularity: Double = 0.0,
    val voteAverage: Double = 0.0
)