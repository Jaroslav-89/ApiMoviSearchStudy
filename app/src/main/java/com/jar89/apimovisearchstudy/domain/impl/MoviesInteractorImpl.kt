package com.jar89.apimovisearchstudy.domain.impl

import com.jar89.apimovisearchstudy.domain.api.MoviesInteractor
import com.jar89.apimovisearchstudy.domain.api.MoviesRepository
import com.jar89.apimovisearchstudy.domain.models.Movie
import com.jar89.apimovisearchstudy.utill.Resource
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        executor.execute {
            when (val resource = repository.searchMovies(expression)) {
                is Resource.Success -> {
                    consumer.consume(resource.data, null)
                }

                is Resource.Error -> {
                    consumer.consume(null, resource.message)
                }
            }
        }
    }

    override fun getMoviesDetails(
        movieId: String,
        consumer: MoviesInteractor.MovieDetailsConsumer
    ) {
        executor.execute {
            when (val resource = repository.searchMovieDetails(movieId)) {
                is Resource.Success -> {
                    consumer.consume(resource.data, null)
                }

                is Resource.Error -> {
                    consumer.consume(null, resource.message)
                }
            }
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        repository.addMovieToFavorites(movie)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        repository.removeMovieFromFavorites(movie)
    }
}