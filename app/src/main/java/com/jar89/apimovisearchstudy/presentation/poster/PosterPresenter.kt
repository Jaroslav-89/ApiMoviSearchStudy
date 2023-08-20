package com.jar89.apimovisearchstudy.presentation.poster




class PosterPresenter(private val view: PosterView, private val imgUrl: String) {

    fun setImg() {
        view.setPosterImage(imgUrl)
    }
}