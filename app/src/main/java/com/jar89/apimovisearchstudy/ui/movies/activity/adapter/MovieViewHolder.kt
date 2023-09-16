package com.jar89.apimovisearchstudy.ui.movies.activity.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jar89.apimovisearchstudy.R
import com.jar89.apimovisearchstudy.domain.models.Movie
import com.jar89.apimovisearchstudy.ui.movies.activity.adapter.MovieAdapter

class MovieViewHolder(
    parent: ViewGroup,
    // 1
    private val clickListener: MovieAdapter.MovieClickListener,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
) {

    var cover: ImageView = itemView.findViewById(R.id.image)
    var title: TextView = itemView.findViewById(R.id.title)
    var description: TextView = itemView.findViewById(R.id.description)
    var inFavoriteToggle: ImageView = itemView.findViewById(R.id.favorite)

    fun bind(movie: Movie) {
        Glide.with(itemView)
            .load(movie.image)
            .into(cover)

        title.text = movie.title
        description.text = movie.description

        inFavoriteToggle.setImageDrawable(getFavoriteToggleDrawable(movie.inFavorite))

        // 2
        itemView.setOnClickListener { clickListener.onMovieClick(movie) }
        // 3
        inFavoriteToggle.setOnClickListener { clickListener.onFavoriteToggleClick(movie) }
    }

    private fun getFavoriteToggleDrawable(inFavorite: Boolean): Drawable? {
        return itemView.context.getDrawable(
            if(inFavorite) R.drawable.active_favorite else R.drawable.inactive_favorite
        )
    }
}