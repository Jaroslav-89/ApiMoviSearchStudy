package com.jar89.apimovisearchstudy.ui.poster.model

import com.jar89.apimovisearchstudy.domain.models.MovieDetails

sealed interface AboutState {
    data class CONTENT(val movie: MovieDetails) : AboutState
    data class ERROR(val errorMassege: String) : AboutState
}