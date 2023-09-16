package com.jar89.apimovisearchstudy.domain.api

import com.jar89.apimovisearchstudy.domain.models.Movie
import com.jar89.apimovisearchstudy.domain.models.MovieDetails
import com.jar89.apimovisearchstudy.utill.Resource

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
    fun searchMovieDetails(movieId: String): Resource<MovieDetails>
    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)
}