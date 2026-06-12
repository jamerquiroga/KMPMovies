package com.jquiroga.kmpmovies.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jquiroga.kmpmovies.data.MovieDetail
import com.jquiroga.kmpmovies.data.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailViewModel(private val movieId: Int) : ViewModel(), KoinComponent {

    private val movieRepository: MovieRepository by inject()
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            val movieDetail = movieRepository.getMovieById(movieId)
            _state.value = UiState(
                loading = false,
                movieDetail = MovieDetail(
                    title = movieDetail.title,
                    overview = movieDetail.overview,
                    backdropPath = "https://image.tmdb.org/t/p/w500/${movieDetail.backdropPath.orEmpty()}",
                    releaseDate = movieDetail.releaseDate,
                    originalTitle = movieDetail.originalTitle,
                    originalLanguage = movieDetail.originalLanguage,
                    popularity = movieDetail.popularity,
                    voteAverage = movieDetail.voteAverage
                )
            )
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movieDetail: MovieDetail = MovieDetail()
    )
}