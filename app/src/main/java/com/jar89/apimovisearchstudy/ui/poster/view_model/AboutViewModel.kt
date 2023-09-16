package com.jar89.apimovisearchstudy.ui.poster.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jar89.apimovisearchstudy.domain.api.MoviesInteractor
import com.jar89.apimovisearchstudy.domain.models.MovieDetails
import com.jar89.apimovisearchstudy.ui.poster.model.AboutState

class AboutViewModel(
    private val movieId: String,
    private val moviesInteractor: MoviesInteractor, application: Application
) : AndroidViewModel(application) {

    private val _state = MutableLiveData<AboutState>()

    fun state(): LiveData<AboutState> = _state

    init {
        moviesInteractor.getMoviesDetails(movieId, object : MoviesInteractor.MovieDetailsConsumer {
            override fun consume(movieDetails: MovieDetails?, errorMessage: String?) {
                if (movieDetails != null) {
                    _state.postValue(AboutState.CONTENT(movieDetails))
                } else {
                    _state.postValue(AboutState.ERROR(errorMessage ?: "Unknown error"))
                }
            }
        })
    }
}