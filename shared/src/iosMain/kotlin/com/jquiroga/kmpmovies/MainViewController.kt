package com.jquiroga.kmpmovies

import androidx.compose.ui.window.ComposeUIViewController
import com.jquiroga.kmpmovies.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}