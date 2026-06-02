package com.jquiroga.kmpmovies.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jquiroga.kmpmovies.data.Movie
import com.jquiroga.kmpmovies.data.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            delay(1000L)
            val movies = movieRepository.getPopularMovies().results.map {
                Movie(
                    id = it.id,
                    title = it.title,
                    poster = "https://image.tmdb.org/t/p/w500/${it.posterPath.orEmpty()}",
                    overview = it.overview
                )
            }
            state = UiState(loading = false, movies = movies)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}