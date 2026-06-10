package com.jquiroga.kmpmovies.di

import androidx.room.RoomDatabase
import com.jquiroga.kmpmovies.BuildConfig
import com.jquiroga.kmpmovies.data.MovieRepository
import com.jquiroga.kmpmovies.data.MovieService
import com.jquiroga.kmpmovies.data.RegionRepository
import com.jquiroga.kmpmovies.data.database.MoviesDao
import com.jquiroga.kmpmovies.data.database.MoviesDataBase
import com.jquiroga.kmpmovies.ui.screens.detail.DetailViewModel
import com.jquiroga.kmpmovies.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single(named("apiKey")) { BuildConfig.API_KEY }
    single<MoviesDataBase> {
        val databaseBuilder = get<RoomDatabase.Builder<MoviesDataBase>>()
        databaseBuilder.build()
    }
    single<MoviesDao> { get<MoviesDataBase>().moviesDao() }
}

val dataModule = module {
    factoryOf(::MovieRepository)
    factoryOf(::RegionRepository)
    factoryOf(::MovieService)
    single {
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
                    parameters.append("api_key", BuildConfig.API_KEY)
                }
            }
        }
    }
}

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}

expect val nativeModule: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule, dataModule, viewModelModule, nativeModule)
    }
}