package com.aleksandrov.breakingbad.di

import android.app.Application
import com.aleksandrov.breakingbad.di.modules.*
import com.aleksandrov.breakingbad.presentation.characterdetails.DetailsActivity
import com.aleksandrov.breakingbad.presentation.characters.CharactersActivity
import com.aleksandrov.breakingbad.presentation.deaths.DeathsActivity
import com.aleksandrov.breakingbad.presentation.episodedetails.EpisodeDetailsActivity
import com.aleksandrov.breakingbad.presentation.episodes.EpisodesActivity
import com.aleksandrov.breakingbad.presentation.quotes.QuotesActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ContextModule::class, NetworkModule::class, MainModule::class,
    UtilsModule::class, DBModule::class, ViewModelsModule::class,
    InteractorsModule::class, AdaptersModule::class, RoomModule::class
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

    fun inject(activity: DetailsActivity)

    fun inject(activity: EpisodesActivity)

    fun inject(activity: EpisodeDetailsActivity)

    fun inject(activity: QuotesActivity)

}