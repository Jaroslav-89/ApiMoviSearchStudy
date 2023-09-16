package com.jar89.apimovisearchstudy.di

import com.jar89.apimovisearchstudy.ui.movies.view_model.MoviesSearchViewModel
import com.jar89.apimovisearchstudy.ui.poster.view_model.AboutViewModel
import com.jar89.apimovisearchstudy.ui.poster.view_model.PosterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MoviesSearchViewModel(get(), get())
    }

    viewModel { (movieId: String) ->
        AboutViewModel(movieId, get(), get())
    }

    viewModel { (posterUrl: String) ->
        PosterViewModel(get(), posterUrl)
    }
}