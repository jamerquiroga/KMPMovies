package com.jquiroga.kmpmovies.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.jquiroga.kmpmovies.data.MovieDetail
import com.jquiroga.kmpmovies.ui.commom.LoadingIndicator
import com.jquiroga.kmpmovies.ui.screens.Screen
import kmpmovies.shared.generated.resources.Res
import kmpmovies.shared.generated.resources.back
import kmpmovies.shared.generated.resources.original_language
import kmpmovies.shared.generated.resources.original_title
import kmpmovies.shared.generated.resources.popularity
import kmpmovies.shared.generated.resources.release_date
import kmpmovies.shared.generated.resources.vote_average
import org.jetbrains.compose.resources.stringResource

@Composable
fun DetailScreen(viewModel: DetailViewModel, onBack: () -> Unit) {
    val state = viewModel.state
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Screen {
        Scaffold(
            topBar = {
                DetailTopBar(
                    title = state.movieDetail.title,
                    onBack = onBack,
                    scrollBehavior = scrollBehavior
                )
            }
        ) { paddingValues ->

            LoadingIndicator(
                enable = state.loading,
                modifier = Modifier.padding(paddingValues)
            )

            MovieDetail(
                modifier = Modifier.padding(paddingValues),
                movieDetail = state.movieDetail
            )
        }
    }
}

@Composable
private fun DetailTopBar(
    title: String,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.back)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun MovieDetail(
    modifier: Modifier = Modifier,
    movieDetail: MovieDetail
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = movieDetail.backdropPath,
            contentDescription = movieDetail.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        )
        Text(
            text = movieDetail.overview,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = buildAnnotatedString {
                property(
                    name = stringResource(Res.string.original_language),
                    value = movieDetail.originalLanguage
                )
                property(
                    name = stringResource(Res.string.original_title),
                    value = movieDetail.originalTitle
                )
                property(
                    name = stringResource(Res.string.release_date),
                    value = movieDetail.releaseDate
                )
                property(
                    name = stringResource(Res.string.popularity),
                    value = movieDetail.popularity.toString()
                )
                property(
                    name = stringResource(Res.string.vote_average),
                    value = movieDetail.voteAverage.toString()
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(16.dp)
        )
    }
}

private fun AnnotatedString.Builder.property(
    name: String,
    value: String,
    end: Boolean = false
) {
    withStyle(ParagraphStyle(lineHeight = 18.sp)) {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("$name : ")
        }
        append(value)
        if (!end) {
            append("\n")
        }
    }
}