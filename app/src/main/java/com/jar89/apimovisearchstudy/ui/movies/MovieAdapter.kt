package com.jar89.apimovisearchstudy.ui.movies

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jar89.apimovisearchstudy.domain.models.Movie

class MovieAdapter(private val clickListener: MovieClickListener) : RecyclerView.Adapter<MovieViewHolder>() {

    var movies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(parent)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener { clickListener.onMovieClick(movies.get(position)) }
    }

    override fun getItemCount() = movies.size

    fun interface MovieClickListener {
        fun onMovieClick(movie: Movie)
    }
}