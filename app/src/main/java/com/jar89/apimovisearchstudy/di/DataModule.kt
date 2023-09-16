package com.jar89.apimovisearchstudy.di

import android.content.Context
import com.google.gson.Gson
import com.jar89.apimovisearchstudy.data.NetworkClient
import com.jar89.apimovisearchstudy.data.network.MovieSearchApi
import com.jar89.apimovisearchstudy.data.network.RetrofitNetworkClient
import com.jar89.apimovisearchstudy.data.storage.LocalStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<MovieSearchApi> {
        Retrofit.Builder()
            .baseUrl("https://imdb-api.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieSearchApi::class.java)
    }

    single {
        androidContext()
            .getSharedPreferences("local_storage", Context.MODE_PRIVATE)
    }

    factory { Gson() }

    single<LocalStorage> {
        LocalStorage(get())
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get(), androidContext())
    }

}