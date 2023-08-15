package com.jar89.apimovisearchstudy.ui.poster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jar89.apimovisearchstudy.utill.Creator
import com.jar89.apimovisearchstudy.R

class PosterActivity : AppCompatActivity() {
    private val posterController = Creator.providePosterController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)
        posterController.onCreate()
    }
}