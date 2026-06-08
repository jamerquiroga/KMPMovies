package com.jquiroga.kmpmovies.di

import com.jquiroga.kmpmovies.data.database.getDatabaseBuilder
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder(get()) }
}