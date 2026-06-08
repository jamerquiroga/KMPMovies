package com.jquiroga.kmpmovies

import android.app.Application
import com.jquiroga.kmpmovies.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MoviesApplication)
        }
    }
}