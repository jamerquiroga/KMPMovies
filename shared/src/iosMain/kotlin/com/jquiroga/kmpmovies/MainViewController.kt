package com.jquiroga.kmpmovies

import androidx.compose.ui.window.ComposeUIViewController
import com.jquiroga.kmpmovies.data.database.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController {
    val database = getDatabaseBuilder().build()
    App(moviesDao = database.moviesDao())
}