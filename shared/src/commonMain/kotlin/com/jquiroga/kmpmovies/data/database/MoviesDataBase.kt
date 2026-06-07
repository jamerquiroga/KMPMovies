package com.jquiroga.kmpmovies.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

@Database(
    entities = [MovieEntity::class],
    version = 1
)
@ConstructedBy(MoviesDatabaseConstructor::class)
abstract class MoviesDataBase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}

const val DATABASE_NAME = "movie.db"

@Suppress("KotlinNoActualForExpect")
expect object MoviesDatabaseConstructor :
    RoomDatabaseConstructor<MoviesDataBase> {
    override fun initialize(): MoviesDataBase
}