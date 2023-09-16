package com.jar89.apimovisearchstudy.ui.poster.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jar89.apimovisearchstudy.ui.poster.model.PosterState

class PosterViewModel(application: Application, private val posterUrl: String) :
    AndroidViewModel(application) {

    private var _state = MutableLiveData<PosterState>(PosterState.ERROR("omg"))

    init {
        if (!posterUrl.isNullOrEmpty()) {
            _state.postValue(PosterState.CONTENT(posterUrl))
        } else {
            _state.postValue(PosterState.ERROR("Unknown error"))
        }
    }

    val state: LiveData<PosterState>
        get() = _state
}