package com.jar89.apimovisearchstudy.utill

import android.content.Context
import com.jar89.apimovisearchstudy.data.MoviesRepositoryImpl
import com.jar89.apimovisearchstudy.data.network.RetrofitNetworkClient
import com.jar89.apimovisearchstudy.domain.api.MoviesInteractor
import com.jar89.apimovisearchstudy.domain.api.MoviesRepository
import com.jar89.apimovisearchstudy.domain.impl.MoviesInteractorImpl
import com.jar89.apimovisearchstudy.presentation.movies.MoviesSearchPresenter
import com.jar89.apimovisearchstudy.presentation.poster.PosterPresenter
import com.jar89.apimovisearchstudy.presentation.movies.MoviesView
import com.jar89.apimovisearchstudy.presentation.poster.PosterView

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchPresenter(
        moviesView: MoviesView,
        context: Context
    ): MoviesSearchPresenter {
        return MoviesSearchPresenter(
            view = moviesView,
            context = context
        )
    }

    fun providePosterPresenter(posterView: PosterView, imgUrl: String): PosterPresenter {
        return PosterPresenter(view = posterView, imgUrl = imgUrl)
    }
}