package com.jar89.apimovisearchstudy.presentation.movies

import com.jar89.apimovisearchstudy.ui.movies.model.MoviesState
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MoviesView : MvpView {

//    // Методы, меняющие внешний вид экрана
//
//    // Состояние «загрузки»
//    fun showLoading()
//
//    // Состояние «ошибки»
//    fun showError(errorMessage: String)
//
//    // Состояние «пустого списка»
//    fun showEmpty(emptyMessage: String)
//
//    // Состояние «контента»
//    fun showContent(movies: List<Movie>)

    // Методы, меняющие внешний вид экрана

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun render(state: MoviesState)

    // Методы «одноразовых событий»

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(additionalMessage: String)
}