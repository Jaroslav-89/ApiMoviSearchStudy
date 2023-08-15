package com.jar89.apimovisearchstudy.data

import com.jar89.apimovisearchstudy.data.dto.MoviesSearchRequest
import com.jar89.apimovisearchstudy.data.dto.MoviesSearchResponse
import com.jar89.apimovisearchstudy.domain.api.MoviesRepository
import com.jar89.apimovisearchstudy.domain.models.Movie
import com.jar89.apimovisearchstudy.utill.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description)})
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}