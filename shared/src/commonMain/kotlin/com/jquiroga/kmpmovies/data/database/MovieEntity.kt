package com.jquiroga.kmpmovies.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterPath: String
)
