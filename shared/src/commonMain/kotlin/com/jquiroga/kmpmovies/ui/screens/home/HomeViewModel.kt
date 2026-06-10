package com.jquiroga.kmpmovies.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jquiroga.kmpmovies.data.Movie
import com.jquiroga.kmpmovies.data.MovieRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    fun onUiReady() {
        viewModelScope.launch {
            state = UiState(loading = true)
            movieRepository.movies.collect {
                if (it.isNotEmpty()) {
                    val mappedData = it.map { movie ->
                        Movie(
                            id = movie.id,
                            title = movie.title,
                            poster = "https://image.tmdb.org/t/p/w500/${movie.posterPath}"
                        )
                    }
                    state = UiState(loading = false, movies = mappedData)
                } else {
                    state = UiState(loading = false)
                }
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}