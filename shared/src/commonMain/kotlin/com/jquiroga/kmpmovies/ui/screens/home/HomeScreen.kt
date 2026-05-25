package com.jquiroga.kmpmovies.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jquiroga.kmpmovies.Movie
import com.jquiroga.kmpmovies.movies
import com.jquiroga.kmpmovies.ui.screens.Screen
import kmpmovies.shared.generated.resources.Res
import kmpmovies.shared.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen() {
    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(Res.string.app_name))
                    },
                    scrollBehavior = scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(paddingValues)
            ) {
                items(movies, key = { it.id }) {
                    Movie(movie = it)
                }
            }
        }
    }
}

@Composable
fun Movie(movie: Movie) {
    Column {
        AsyncImage(
            model = movie.poster,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 3f)
                .clip(MaterialTheme.shapes.small)
        )

        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            modifier = Modifier.padding(8.dp)
        )
    }
}