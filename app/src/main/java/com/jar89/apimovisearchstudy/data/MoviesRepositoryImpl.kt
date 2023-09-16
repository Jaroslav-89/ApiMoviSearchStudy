package com.jar89.apimovisearchstudy.data

import android.util.Log
import com.jar89.apimovisearchstudy.data.dto.MovieDetailsRequest
import com.jar89.apimovisearchstudy.data.dto.MovieDetailsResponse
import com.jar89.apimovisearchstudy.data.dto.MoviesSearchRequest
import com.jar89.apimovisearchstudy.data.dto.MoviesSearchResponse
import com.jar89.apimovisearchstudy.data.storage.LocalStorage
import com.jar89.apimovisearchstudy.domain.api.MoviesRepository
import com.jar89.apimovisearchstudy.domain.models.Movie
import com.jar89.apimovisearchstudy.domain.models.MovieDetails
import com.jar89.apimovisearchstudy.utill.Resource

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: LocalStorage,
) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }

            200 -> {
                val stored = localStorage.getSavedFavorites()

                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(
                        id = it.id,
                        resultType = it.resultType,
                        image = it.image,
                        title = it.title,
                        description = it.description,
                        inFavorite = stored.contains(it.id),
                    )
                })
            }

            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

    override fun searchMovieDetails(movieId: String): Resource<MovieDetails> {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }

            200 -> {
                Log.d("XXX", "УСПЕШНЫЙ ЗАПРОС")
                with(response as MovieDetailsResponse) {
                    Resource.Success(
                        MovieDetails(
                            id, title, imDbRating, year,
                            countries, genres, directors, writers, stars, plot
                        )
                    )
                }
            }

            else -> {
                Resource.Error("Ошибка сервера")

            }
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }
}