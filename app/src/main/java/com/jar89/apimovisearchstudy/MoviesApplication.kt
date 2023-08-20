package com.jar89.apimovisearchstudy

import android.app.Application
import com.jar89.apimovisearchstudy.presentation.movies.MoviesSearchPresenter

class MoviesApplication : Application() {

    var moviesSearchPresenter : MoviesSearchPresenter? = null

}