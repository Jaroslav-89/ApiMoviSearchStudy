package com.jar89.apimovisearchstudy.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.jar89.apimovisearchstudy.data.NetworkClient
import com.jar89.apimovisearchstudy.data.dto.MovieDetailsRequest
import com.jar89.apimovisearchstudy.data.dto.MoviesSearchRequest
import com.jar89.apimovisearchstudy.data.dto.Response

class RetrofitNetworkClient(private val imdbService: MovieSearchApi, private val context: Context) :
    NetworkClient {

    override fun doRequest(dto: Any): Response {
        if (isConnected() == false) {
            return Response().apply { resultCode = -1 }
        }
        if ((dto !is MoviesSearchRequest) && (dto !is MovieDetailsRequest)) {
            return Response().apply { resultCode = 400 }
        }

        val response = if (dto is MoviesSearchRequest) {
            imdbService.searchMovies(dto.expression).execute()
        } else {
            imdbService.getMovieDetails((dto as MovieDetailsRequest).movieId).execute()
        }
        val body = response.body()
        return if (body != null) {
            body.apply { resultCode = response.code() }
        } else {
            Response().apply { resultCode = response.code() }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}