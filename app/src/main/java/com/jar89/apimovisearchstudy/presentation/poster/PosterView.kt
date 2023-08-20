package com.jar89.apimovisearchstudy.presentation.poster

import moxy.MvpView

interface PosterView : MvpView {

    fun setPosterImage(imgUrl: String)
}