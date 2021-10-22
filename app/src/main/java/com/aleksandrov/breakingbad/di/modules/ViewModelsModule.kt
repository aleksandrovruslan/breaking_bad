package com.aleksandrov.breakingbad.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleksandrov.breakingbad.di.ViewModelKey
import com.aleksandrov.breakingbad.domain.BBInteractor
import com.aleksandrov.breakingbad.domain.CharactersInteractor
import com.aleksandrov.breakingbad.domain.DetailsInteractor
import com.aleksandrov.breakingbad.presentation.characterdetails.DetailsViewModel
import com.aleksandrov.breakingbad.presentation.characters.CharactersViewModel
import com.aleksandrov.breakingbad.presentation.deaths.DeathCountViewModel
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
    fun providesBBViewModel(
        interactor: BBInteractor,
        schedulers: SchedulersProvider,
    ): ViewModel {
        return DeathCountViewModel(interactor, schedulers)
    }

    @ViewModelKey(CharactersViewModel::class)
    @IntoMap
    @Provides
    fun provideCharactersViewModel(
        interactor: CharactersInteractor,
        schedulers: SchedulersProvider,
    ): ViewModel {
        return CharactersViewModel(interactor, schedulers)
    }

    @ViewModelKey(DetailsViewModel::class)
    @IntoMap
    @Provides
    fun provideDetailsViewModel(
        interactor: DetailsInteractor,
        schedulers: SchedulersProvider,
    ): ViewModel {
        return DetailsViewModel(interactor, schedulers)
    }

    @Singleton
    @Provides
    fun provide(viewModelFactories: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(viewModelFactories)
    }

}