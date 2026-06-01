package com.jquiroga.kmpmovies.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jquiroga.kmpmovies.movies
import com.jquiroga.kmpmovies.ui.screens.detail.DetailScreen
import com.jquiroga.kmpmovies.ui.screens.home.HomeScreen
import com.jquiroga.kmpmovies.ui.screens.navigation.DetailRoute
import com.jquiroga.kmpmovies.ui.screens.navigation.HomeRoute

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate(DetailRoute(movie.id))
                }
            )
        }
        composable<DetailRoute> { backStackEntry ->
            val detailRoute = backStackEntry.toRoute<DetailRoute>()
            DetailScreen(
                movie = movies.first { it.id == detailRoute.movieId },
                onBack = { navController.popBackStack() }
            )
        }
    }
}