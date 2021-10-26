package com.aleksandrov.breakingbad.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleksandrov.breakingbad.di.ViewModelKey
import com.aleksandrov.breakingbad.domain.*
import com.aleksandrov.breakingbad.presentation.characterdetails.DetailsViewModel
import com.aleksandrov.breakingbad.presentation.characters.CharactersViewModel
import com.aleksandrov.breakingbad.presentation.deaths.DeathCountViewModel
import com.aleksandrov.breakingbad.presentation.episodedetails.EpisodeDetailsViewModel
import com.aleksandrov.breakingbad.presentation.episodes.EpisodesViewModel
import com.aleksandrov.breakingbad.presentation.quotes.QuotesViewModel
import com.aleksandrov.breakingbad.utils.SchedulersProvider
import com.aleksandrov.breakingbad.utils.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import javax.inject.Singleton

@Module
class ViewModelsModule {

    @ViewModelKey(DeathCountViewModel::class)
    @IntoMap
    @Provides
    fun providesBBViewModel(interactor: BBInteractor, schedulers: SchedulersProvider): ViewModel =
        DeathCountViewModel(interactor, schedulers)

    @ViewModelKey(CharactersViewModel::class)
    @IntoMap
    @Provides
    fun provideCharactersViewModel(
        interactor: CharactersInteractor,
        schedulers: SchedulersProvider,
    ): ViewModel = CharactersViewModel(interactor, schedulers)

    @ViewModelKey(DetailsViewModel::class)
    @IntoMap
    @Provides
    fun provideDetailsViewModel(
        interactor: DetailsInteractor,
        schedulers: SchedulersProvider,
    ): ViewModel = DetailsViewModel(interactor, schedulers)

    @ViewModelKey(EpisodesViewModel::class)
    @IntoMap
    @Provides
    fun provideEpisodesViewModel(
        interactor: EpisodesInteractor,
        schedulers: SchedulersProvider,
    ): ViewModel = EpisodesViewModel(interactor, schedulers)

    @ViewModelKey(EpisodeDetailsViewModel::class)
    @IntoMap
    @Provides
    fun provideEpisodeDetailsViewModel(
        interactor: EpisodeDetainsInteractor,
        schedulers: SchedulersProvider,
    ): ViewModel = EpisodeDetailsViewModel(interactor, schedulers)

    @ViewModelKey(QuotesViewModel::class)
    @IntoMap
    @Provides
    fun provideQuotesViewModel(
        interactor: QuotesInteractor,
        schedulers: SchedulersProvider,
    ): ViewModel = QuotesViewModel(interactor, schedulers)

    @Singleton
    @Provides
    fun provideViewModelFactory(
        viewModelFactories: Map<Class<out ViewModel>,
                @JvmSuppressWildcards Provider<ViewModel>>,
    ): ViewModelProvider.Factory {
        return ViewModelFactory(viewModelFactories)
    }

}