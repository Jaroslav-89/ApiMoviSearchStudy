package com.jar89.apimovisearchstudy.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jar89.apimovisearchstudy.R
import com.jar89.apimovisearchstudy.domain.models.Movie


class MovieViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
    ) {

    private val image: ImageView = itemView.findViewById(R.id.image)
    private val title: TextView = itemView.findViewById(R.id.title)
    private val description: TextView = itemView.findViewById(R.id.description)

    fun bind(movie: Movie) {
        Glide.with(itemView)
            .load(movie.image)
            .placeholder(R.drawable.place_holder)
            .into(image)

        title.text = movie.title
        description.text = movie.description
    }
}