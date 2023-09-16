package com.jar89.apimovisearchstudy

import android.app.Application
import com.jar89.apimovisearchstudy.di.dataModule
import com.jar89.apimovisearchstudy.di.interactorModule
import com.jar89.apimovisearchstudy.di.repositoryModule
import com.jar89.apimovisearchstudy.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(dataModule, repositoryModule, interactorModule, viewModelModule)
        }
    }


}