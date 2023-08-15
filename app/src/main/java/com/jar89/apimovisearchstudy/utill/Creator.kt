package com.jar89.apimovisearchstudy.utill

import android.app.Activity
import android.content.Context
import com.jar89.apimovisearchstudy.data.MoviesRepositoryImpl
import com.jar89.apimovisearchstudy.data.network.RetrofitNetworkClient
import com.jar89.apimovisearchstudy.domain.api.MoviesInteractor
import com.jar89.apimovisearchstudy.domain.api.MoviesRepository
import com.jar89.apimovisearchstudy.domain.impl.MoviesInteractorImpl
import com.jar89.apimovisearchstudy.presentation.movies.MoviesSearchPresenter
import com.jar89.apimovisearchstudy.presentation.PosterController
import com.jar89.apimovisearchstudy.presentation.movies.MoviesView
import com.jar89.apimovisearchstudy.ui.movies.MovieAdapter

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchPresenter(
        moviesView: MoviesView,
        adapter: MovieAdapter
    ): MoviesSearchPresenter {
        return MoviesSearchPresenter(
            view = moviesView,
            adapter = adapter
        )
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}