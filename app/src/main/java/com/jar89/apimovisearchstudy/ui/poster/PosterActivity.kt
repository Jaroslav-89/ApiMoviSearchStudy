package com.jar89.apimovisearchstudy.ui.poster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jar89.apimovisearchstudy.utill.Creator
import com.jar89.apimovisearchstudy.R
import com.jar89.apimovisearchstudy.presentation.poster.PosterPresenter
import com.jar89.apimovisearchstudy.presentation.poster.PosterView

class PosterActivity : AppCompatActivity(), PosterView {

    private lateinit var posterPresenter: PosterPresenter
    private lateinit var poster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)

        poster = findViewById(R.id.poster)
        val imageUrl = intent.extras?.getString("poster", "") ?: ""
        posterPresenter = Creator.providePosterPresenter(this, imageUrl)

        posterPresenter.setImg()
    }

    override fun setPosterImage(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(poster)
    }
}