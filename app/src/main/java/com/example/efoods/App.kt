package com.example.efoods

import android.app.Application
import android.content.Context
import com.example.efoods.di.AppComponent
import com.example.efoods.di.DaggerAppComponent
import com.example.efoods.di.modules.AppModule


class App: Application() {
    companion object {
        lateinit var instance: App
    }


    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

    }

    fun getAppContext(): Context {
        return instance.applicationContext
    }

}