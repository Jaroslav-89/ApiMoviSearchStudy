package com.jar89.apimovisearchstudy

import android.app.Activity
import android.content.Context
import com.jar89.apimovisearchstudy.data.MoviesRepositoryImpl
import com.jar89.apimovisearchstudy.data.network.RetrofitNetworkClient
import com.jar89.apimovisearchstudy.domain.api.MoviesInteractor
import com.jar89.apimovisearchstudy.domain.api.MoviesRepository
import com.jar89.apimovisearchstudy.domain.impl.MoviesInteractorImpl
import com.jar89.apimovisearchstudy.presentation.MoviesSearchController
import com.jar89.apimovisearchstudy.presentation.PosterController
import com.jar89.apimovisearchstudy.ui.movies.MovieAdapter

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchController(activity: Activity, adapter: MovieAdapter): MoviesSearchController {
        return MoviesSearchController(activity, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}