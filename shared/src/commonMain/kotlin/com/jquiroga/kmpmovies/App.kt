package com.jquiroga.kmpmovies

import androidx.compose.runtime.*
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.jquiroga.kmpmovies.data.database.MoviesDao
import com.jquiroga.kmpmovies.ui.screens.Navigation


@Composable
fun App(moviesDao: MoviesDao) {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .crossfade(true)
            .logger(DebugLogger())
            .build()
    }

    Navigation(moviesDao)
}