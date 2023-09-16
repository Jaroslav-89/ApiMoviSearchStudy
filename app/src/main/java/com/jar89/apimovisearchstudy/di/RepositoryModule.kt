package com.jar89.apimovisearchstudy.di

import com.jar89.apimovisearchstudy.data.MoviesRepositoryImpl
import com.jar89.apimovisearchstudy.domain.api.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get())
    }
}