package com.aleksandrov.breakingbad

import android.app.Application
import android.content.Context
import com.aleksandrov.breakingbad.di.AppComponent
import com.aleksandrov.breakingbad.di.DaggerAppComponent

class App : Application() {

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().create(this).build()
    }

    /**
     * Получить компонент приложения
     */
    fun getComponent(): AppComponent {
        return component
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> getComponent()
        else -> this.applicationContext.appComponent
    }