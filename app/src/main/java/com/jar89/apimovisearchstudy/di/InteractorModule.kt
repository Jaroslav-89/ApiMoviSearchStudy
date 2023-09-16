package com.jar89.apimovisearchstudy.di

import com.jar89.apimovisearchstudy.domain.api.MoviesInteractor
import com.jar89.apimovisearchstudy.domain.impl.MoviesInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    single<MoviesInteractor> {
        MoviesInteractorImpl(get())
    }
}