package com.aleksandrov.breakingbad.di

import android.app.Application
import com.aleksandrov.breakingbad.di.modules.*
import com.aleksandrov.breakingbad.presentation.characterdetails.DetailsActivity
import com.aleksandrov.breakingbad.presentation.characters.CharactersActivity
import com.aleksandrov.breakingbad.presentation.deaths.DeathsActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ContextModule::class, NetworkModule::class, MainModule::class,
    UtilsModule::class, DBModule::class, ViewModelsModule::class,
    InteractorsModule::class, AdaptersModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(activity: DeathsActivity)

    fun inject(activity: CharactersActivity)

    fun inject(detailsActivity: DetailsActivity)

}