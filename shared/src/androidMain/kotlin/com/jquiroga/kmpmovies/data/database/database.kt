package com.jquiroga.kmpmovies.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<MoviesDataBase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<MoviesDataBase>(
        context = context,
        name = dbFile.absolutePath
    )
}