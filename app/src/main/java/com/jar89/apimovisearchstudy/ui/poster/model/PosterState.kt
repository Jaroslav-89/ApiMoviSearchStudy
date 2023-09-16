package com.jar89.apimovisearchstudy.ui.poster.model

sealed interface PosterState {
    data class CONTENT(val posterUrl: String) : PosterState
    data class ERROR(val errorMassege: String) : PosterState
}
