package com.jquiroga.kmpmovies.di

import com.jquiroga.kmpmovies.data.IosRegionDataSource
import com.jquiroga.kmpmovies.data.RegionDataSource
import com.jquiroga.kmpmovies.data.database.getDatabaseBuilder
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder() }
    factoryOf(::IosRegionDataSource) bind RegionDataSource::class
}