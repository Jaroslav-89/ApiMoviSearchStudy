package com.jar89.apimovisearchstudy.ui.movies.model

sealed interface ToastState {
    object None : ToastState
    data class Show(val additionalMessage: String) : ToastState
}