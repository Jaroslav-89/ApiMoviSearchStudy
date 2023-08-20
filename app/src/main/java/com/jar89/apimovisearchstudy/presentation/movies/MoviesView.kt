package com.jar89.apimovisearchstudy.presentation.movies

import com.jar89.apimovisearchstudy.domain.models.Movie
import com.jar89.apimovisearchstudy.ui.movies.model.MoviesState

interface MoviesView {

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

    fun render(state: MoviesState)

    // Методы «одноразовых событий»

    fun showToast(additionalMessage: String)
}