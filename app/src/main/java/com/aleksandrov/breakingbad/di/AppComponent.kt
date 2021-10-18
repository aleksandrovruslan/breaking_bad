package com.aleksandrov.breakingbad.di

import android.app.Application
import com.aleksandrov.breakingbad.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, MessageModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)

}