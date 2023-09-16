package com.jar89.apimovisearchstudy.ui.movies.activity.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jar89.apimovisearchstudy.domain.models.Movie

class MovieAdapter(private val clickListener: MovieClickListener) :
    RecyclerView.Adapter<MovieViewHolder>() {

    var movies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        // 1
        MovieViewHolder(parent, clickListener)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        // 2
        holder.bind(movies.get(position))
    }

    override fun getItemCount(): Int = movies.size

    interface MovieClickListener {
        fun onMovieClick(movie: Movie)
        fun onFavoriteToggleClick(movie: Movie)
    }
}