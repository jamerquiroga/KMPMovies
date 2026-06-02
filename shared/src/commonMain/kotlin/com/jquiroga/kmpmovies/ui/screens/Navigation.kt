package com.jquiroga.kmpmovies.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jquiroga.kmpmovies.data.MovieRepository
import com.jquiroga.kmpmovies.data.MovieService
import com.jquiroga.kmpmovies.data.movies
import com.jquiroga.kmpmovies.ui.screens.detail.DetailScreen
import com.jquiroga.kmpmovies.ui.screens.home.HomeScreen
import com.jquiroga.kmpmovies.ui.screens.home.HomeViewModel
import com.jquiroga.kmpmovies.ui.screens.navigation.DetailRoute
import com.jquiroga.kmpmovies.ui.screens.navigation.HomeRoute
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kmpmovies.shared.generated.resources.Res
import kmpmovies.shared.generated.resources.api_key
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.stringResource

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val apiKey = stringResource(Res.string.api_key)
    val client = remember {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.themoviedb.org/3"
                    parameters.append("api_key", apiKey)
                }
            }
        }
    }


    val movieService = MovieService(client)

    val viewModel = viewModel { HomeViewModel(movieRepository = MovieRepository(movieService) ) }

    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate(DetailRoute(movie.id))
                },
                viewModel = viewModel
            )
        }
        composable<DetailRoute> { backStackEntry ->
            backStackEntry.toRoute<DetailRoute>()
            DetailScreen(
                movie = movies.first(),
                onBack = { navController.popBackStack() }
            )
        }
    }
}