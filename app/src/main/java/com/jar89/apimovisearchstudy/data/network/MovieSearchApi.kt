package com.jar89.apimovisearchstudy.data.network

import com.jar89.apimovisearchstudy.data.dto.MoviesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieSearchApi {

    @GET ("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
    fun getMovieInfo(@Path("expression") expression: String): Call<MoviesSearchResponse>
}